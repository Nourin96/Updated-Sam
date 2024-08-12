package com.publisher.SamPublisher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.SamPublisher.exception.UserException;
import com.publisher.SamPublisher.exception.ValidationException;
import com.publisher.SamPublisher.model.UserData;
import com.publisher.SamPublisher.serviceImpl.CustomerServiceImpl;
import com.publisher.SamPublisher.serviceImpl.KafkaConsumerServiceImpl;
import com.publisher.SamPublisher.serviceImpl.KafkaProducerServiceImpl;
import com.publisher.SamPublisher.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    KafkaProducerServiceImpl producerService;
    @Autowired
    KafkaConsumerServiceImpl consumerService;

    @Autowired
    CustomerServiceImpl customerService;

    /**
     * @param input
     * @return ResponseEntity<String>
     *     Api fetching parent details for successfully added customer.
     * @throws JsonProcessingException
     * @throws UserException
     */
    @PostMapping("/customerData")
    public ResponseEntity<String> addCustomerData(@RequestBody String input) throws JsonProcessingException, UserException, ValidationException{
           ObjectMapper objectMapper = new ObjectMapper();
           UserData userData;

           String userId=consumerService.userIdListen(input);
           userData= userService.getUserById(userId);

           if(userData!=null) {
               if (customerService.addCustomerData(userData)) {
                   String parentDetails = objectMapper.writeValueAsString(userService.getParentDetails(userData));
                   producerService.sendMessage("publisher", parentDetails);
                   return ResponseEntity.ok("Payload successfully published to subscriber service.");
               } else {
                   return ResponseEntity.ok("Couldn't fetch Parent details ");
               }
           }
        else throw new UserException("User Data null");
        }

}

