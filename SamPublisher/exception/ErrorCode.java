package com.publisher.SamPublisher.exception;

import lombok.*;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorCode {
    private String error;
    private String message;
    private Map<String, String> fields;
    private Integer status;
    private String timestamp;



    /*public ErrorCode(Integer errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }*/
}