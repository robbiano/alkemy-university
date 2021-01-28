package com.example.alkemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class subjectsController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    //subjects
    @PostMapping("{teacherId}")
    public ResponseEntity<Object> createSubject(@PathVariable("teacherId") Long teacherId, @RequestBody SubjectDTO subjectDTO){

        if (subjectDTO.getName() == "" || subjectDTO.getTime() == null || subjectDTO.getAvailability() == null) {
            return new ResponseEntity<>("missing data", HttpStatus.FORBIDDEN);
        }

        if ( subjectDTO.getAvailability() < 1 ) {
            return new ResponseEntity<>("wrong data at Availability", HttpStatus.FORBIDDEN);
        }

        if (teacherId == null ) {
            return new ResponseEntity<>("missing teacher id data", HttpStatus.FORBIDDEN);
        }

        Subject newSubject = new Subject(subjectDTO.getId(), subjectDTO.getName(), subjectDTO.getTime(), teacherRepository.getOne(teacherId), subjectDTO.getAvailability() );
        newSubject = subjectRepository.save(newSubject);
        return new ResponseEntity<>(newSubject.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{subjectId}/{teacherId}")
    public ResponseEntity<Object> updateSubjects(@PathVariable("subjectId") Long subjectId, @PathVariable("teacherId") Long teacherId , @RequestBody SubjectDTO subjectDTO){

        if (subjectDTO.getName() == "" || subjectDTO.getTime() == null || subjectDTO.getAvailability() == null) {
            return new ResponseEntity<>("missing data", HttpStatus.FORBIDDEN);
        }

        if ( subjectDTO.getAvailability() < 1 ) {
            return new ResponseEntity<>("wrong data at Availability", HttpStatus.FORBIDDEN);
        }

        if (teacherId == null ) {
            return new ResponseEntity<>("missing teacher id data", HttpStatus.FORBIDDEN);
        }

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ subjectId));

        subject.setName(subjectDTO.getName());
        subject.setTime(subjectDTO.getTime());
        subject.setTeacher(teacherRepository.getOne(teacherId));
        subject.setAvailability(subjectDTO.getAvailability());

        Subject updatedSubject = subjectRepository.save(subject);
        return new ResponseEntity<>(subject.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("{subjectId}")
    public Map<String, Boolean> deleteSubject(@PathVariable("subjectId") Long subjectId)
            throws ResourceNotFoundException {

        if (subjectId < 1) {
            Map<String, Boolean> response = new HashMap<>();
            response.put("id must be  1 or +1, deleted", Boolean.FALSE);
            return response;
        }

        subjectRepository.deleteById(subjectId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @GetMapping
    public List<SubjectDTO> getAll() {
        return  subjectRepository
        .findAll()
        .stream()
        .map(subject -> new SubjectDTO(subject.getId(), subject.getName(),subject.getTime(),subject.getTeacher() ,subject.getAvailability())).collect(toList());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Object> getSubjectById(@PathVariable("subjectId") Long subjectId){
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(subject.isEmpty()){
            return new ResponseEntity<>("no such subject", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().body(subject);
    }
}
