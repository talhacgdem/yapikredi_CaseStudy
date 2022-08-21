package com.talhacgdem.yapikredi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.talhacgdem.yapikredi.config.Translator.toLocale;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeaveNotFoundException extends RuntimeException {
    public LeaveNotFoundException(String message){
        super(toLocale("leaveNotFound") + " : " + message);
    }
}