package com.publisher.SamPublisher.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.publisher.SamPublisher.exception.UserException;
import com.publisher.SamPublisher.model.UserData;

public interface CustomerService {
    public boolean addCustomerData(UserData userData) throws JsonProcessingException, UserException;

}
