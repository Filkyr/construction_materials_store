package com.netcracker.cmstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingComponent {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingComponent.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        logger.error("Exception while http request handling", ex);

        return "WEB-INF/error.jsp";
    }
}
