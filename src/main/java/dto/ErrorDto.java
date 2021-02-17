package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.logging.Logger;

@AllArgsConstructor
@Data
public class ErrorDto {

    private int statusCode;
    private Date timestamp;
    private String message;

}
