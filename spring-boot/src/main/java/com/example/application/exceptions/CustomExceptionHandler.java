package com.example.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {CustomRequestException.class})
    public ResponseEntity<Object> handleException(CustomRequestException x)
    {
        NotFoundException NotFoundEx = new NotFoundException(
                x.getMessage(),
                x,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(NotFoundEx, HttpStatus.BAD_REQUEST);
    }
}
