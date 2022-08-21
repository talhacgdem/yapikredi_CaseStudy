package com.talhacgdem.yapikredi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.talhacgdem.yapikredi.config.Translator.toLocale;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestVariableNotValidException extends RuntimeException{
    public RequestVariableNotValidException(String message){
        super(toLocale("leaveNotFound") + " : " + message);
    }
}
