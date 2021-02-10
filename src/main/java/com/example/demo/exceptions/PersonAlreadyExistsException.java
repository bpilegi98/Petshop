package com.example.demo.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Log
public class PersonAlreadyExistsException extends Exception{

    public PersonAlreadyExistsException(String message)
    {
        super(message);
        log.info(message);
    }

}
