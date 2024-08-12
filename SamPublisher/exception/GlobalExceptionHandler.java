package com.publisher.SamPublisher.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
        public ResponseEntity<ErrorCode> handleParentException() {
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "UserId is mandatory.");
        fields.put("registeredAt", "RegisteredAt is mandatory.");
        ErrorCode error = new ErrorCode("Validation Error","Mandatory attributes should not be null",
                fields,400,"Something is missing in that code");
            return new ResponseEntity<ErrorCode>(error, HttpStatus.NOT_FOUND);
    }
}