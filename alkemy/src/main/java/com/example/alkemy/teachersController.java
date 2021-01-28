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
@RequestMapping("/api/teachers")
public class teachersController {


    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    //teachers
    @PostMapping
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherDTO teacherDTO){

        if (teacherDTO.getName() == "" ||  teacherDTO.getLastName() == "" ||teacherDTO.getDni() == null) {
            return new ResponseEntity<>("missing data error", HttpStatus.FORBIDDEN);
        }

        Teacher newTeacher = new Teacher(teacherDTO.getId(), teacherDTO.getName(), teacherDTO.getLastName(), teacherDTO.getDni(), teacherDTO.getActive());

        newTeacher = teacherRepository.save(newTeacher);
        return new ResponseEntity<>(newTeacher.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Object> updateTeacher(@PathVariable("teacherId") Long teacherId, @RequestBody TeacherDTO teacherDTO){

        if (teacherDTO.getName() == "" ||  teacherDTO.getLastName() == "" ||teacherDTO.getDni() == null) {
            return new ResponseEntity<>("missing data error", HttpStatus.FORBIDDEN);
        }
        if (teacherId < 1) {
            return new ResponseEntity<>(" teacher id data error", HttpStatus.FORBIDDEN);
        }

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ teacherId));
        teacher.setName(teacherDTO.getName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setDni(teacherDTO.getDni());
        teacher.setActive(teacherDTO.getActive());

        final Teacher updatedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>( teacher.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("{teacherId}")
    public Map<String, Boolean> deleteTeacher(@PathVariable("teacherId") Long teacherId)
            throws ResourceNotFoundException {

        if (teacherId < 1) {
            Map<String, Boolean> response = new HashMap<>();
            response.put(" teacher id data error", Boolean.FALSE);
            return response;
        }

        teacherRepository.deleteById(teacherId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository
                .findAll()
                .stream()
                .map(teacher -> new Teacher(teacher.getId(), teacher.getName(), teacher.getLastName(),teacher.getDni(),teacher.getActive())).collect(toList());
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Object> getTeacherById(@PathVariable("teacherId") Long teacherId){

        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(teacher.isEmpty()){
            return new ResponseEntity<>("no such teacher", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().body(teacher);
    }


}
