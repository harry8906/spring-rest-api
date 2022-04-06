package com.bluesky.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponseEntity> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(customResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity(customResponseEntity(ex, request, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    private CustomResponseEntity customResponseEntity(Exception e, WebRequest request, HttpStatus httpStatus) {
        CustomResponseEntity responseEntity = CustomResponseEntity
                .builder()
                .status(httpStatus.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .desc(request.getDescription(false))
                .build();
        return responseEntity;
    }
}
