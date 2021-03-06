package com.example.demo.exceptions;

import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.dto.ErrorDto;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;
import java.util.logging.Level;

@Log
@org.springframework.web.bind.annotation.ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PetshopAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExistsException(PetshopAlreadyExistsException exception)
    {

        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage()
        );

        log.log(Level.WARNING, "Couldn't add.");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PetshopNotExistsException.class)
    public ResponseEntity<ErrorDto> handleNotExistsException(PetshopNotExistsException exception)
    {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage()
        );

        log.log(Level.WARNING, "Couldn't find.");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException exception)
    {
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage()
        );

        log.log(Level.WARNING, "An error has ocurred.");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
