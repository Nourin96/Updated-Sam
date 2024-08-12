package com.publisher.SamPublisher.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.SamPublisher.dto.Input;
import com.publisher.SamPublisher.exception.UserException;
import com.publisher.SamPublisher.repo.ProductDataRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class KafkaConsumerServiceImpl {

  @KafkaListener(topics = "publisher", groupId = "my-group")
  public String listen(String message) {
    log.info("Received Message: " + message);
    return message;
  }

  public String userIdListen(String message) throws JsonProcessingException, UserException {
    ObjectMapper mapper=new ObjectMapper();
    Input json=mapper.readValue(listen(message), Input.class);
    validateInput(json);
    return json.getUserId();
  }

  private void validateInput(Input json) throws UserException{
    if(json.getUserId()==null||json.getUserId().isEmpty())
      throw new UserException("User Id entered is null or Empty");
    else if (json.getRegisteredAt()==null||json.getRegisteredAt().isEmpty())
      throw new UserException("User Id entered is null or Empty");
  }

}
