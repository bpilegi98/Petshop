package com.example.demo.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Log
public class AppointmentNotExists extends Exception{

    public AppointmentNotExists(String message)
    {
        super(message);
        log.info(message);
    }
}
