package com.app.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidEventUpdateException extends RuntimeException{

    public InvalidEventUpdateException(String message){
        super(message);
    }

}
