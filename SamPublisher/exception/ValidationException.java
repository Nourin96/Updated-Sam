package com.publisher.SamPublisher.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor

public class ValidationException extends RuntimeException {
    private final String message;
    private final int statusCode;
    private final String timestamp;
    private final Map<String, String> fields;

    /*public ValidationException(String message, int statusCode, String timestamp, Map<String, String> fields) {
        super(message);
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.fields = fields;
    }*/

}
