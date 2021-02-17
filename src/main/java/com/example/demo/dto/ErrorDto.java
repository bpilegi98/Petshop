package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorDto {

    private int statusCode;
    private Date timestamp;
    private String message;

}
