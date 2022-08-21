package com.talhacgdem.yapikredi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.talhacgdem.yapikredi.config.Translator.toLocale;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ExceedLeaveRightsException extends RuntimeException{
    public ExceedLeaveRightsException(){
        super(toLocale("exceedLeaveRight"));
    }
}
