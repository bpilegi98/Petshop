package com.example.demo.controller;

import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.exceptions.PetshopNotExistsException;
import dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PetshopAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExistsException(PetshopAlreadyExistsException exception)
    {
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage()
        );

        return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PetshopNotExistsException.class)
    public ResponseEntity<ErrorDto> handleNotExistsException(PetshopNotExistsException exception)
    {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage()
        );

        return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException exception)
    {
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage()
        );

        return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
    }
}
