package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PetshopAlreadyExistsException extends Exception{

    public PetshopAlreadyExistsException(String message)
    {
        super(message);
    }

}
