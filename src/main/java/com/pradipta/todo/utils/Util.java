package com.pradipta.todo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class Util {
    public static ResponseEntity<String> createResponseEntity(String message, HttpStatus statusCode) {
        return new ResponseEntity<>(message, statusCode);
    }
}
