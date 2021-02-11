package com.example.demo.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PetshopAlreadyExistsException extends Exception{

    public PetshopAlreadyExistsException(String message)
    {
        super(message);
    }

}
