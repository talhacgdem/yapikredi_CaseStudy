package com.talhacgdem.yapikredi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {
    private static ResourceBundleMessageSource resourceBundleMessageSource;

    public Translator(@Qualifier("messages") ResourceBundleMessageSource resourceBundleMessageSource){
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public static String toLocale(String code){
        Locale locale = LocaleContextHolder.getLocale();
        return resourceBundleMessageSource.getMessage(code, null, locale);
    }

    public static String toLocale(String code, Object[] args){
        Locale locale = LocaleContextHolder.getLocale();
        return resourceBundleMessageSource.getMessage(code, args, locale);
    }
}
