package com.dct.News_Application.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> bookNotFound(UserNotFoundException error, WebRequest request) {
        Map<String , String> errMessage = new HashMap<>();
        errMessage.put("error", error.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errMessage);
    }
}
