package com.app.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidEventTypeException extends RuntimeException{
    public InvalidEventTypeException(String message){
        super(message);
    }
}
