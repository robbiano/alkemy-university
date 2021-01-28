package com.example.alkemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static java.util.stream.Collectors.toList;

import java.util.*;

@RestController
@RequestMapping("/api/subscriptions")
public class subscriptionsController {

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    //subscriptions

    @PostMapping("/{studentId}/{subjectId}")
    public ResponseEntity<Long> createSubscription(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId){

        Subscriptions newSubscriptions = new Subscriptions(subjectRepository.getOne(studentId), userRepository.getOne(subjectId) );
        newSubscriptions = subscriptionsRepository.save(newSubscriptions);
        return new ResponseEntity<>(newSubscriptions.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{subscriptionId}")
    public Map<String, Boolean> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId)
            throws ResourceNotFoundException {
        Subscriptions subscription = subscriptionsRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("subscription not found  :: " + subscriptionId));

        subscriptionsRepository.delete(subscription);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping
    public List<SubscriptionsDTO> getAll() {
         return  subscriptionsRepository
         .findAll()
         .stream()
         .map(subscription -> new SubscriptionsDTO(subscription.getId(), subscription.getSubject(),subscription.getStudent())).collect(toList());
    }
}
