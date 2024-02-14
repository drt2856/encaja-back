package com.encaja.infraestructure.controllerexceptionhandler;


import com.encaja.infraestructure.Exeptions.BadAutentication;
import com.encaja.infraestructure.dto.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@ControllerAdvice()
public class GeneralExceptionHandler {

    private Logger LOGGER;

    private ErrorMessage errorMessage;


    public GeneralExceptionHandler() {
        LOGGER = LoggerFactory.getLogger("error");

    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorMessage handleExpiredJwtException(ExpiredJwtException ex) {
        errorMessage= buildErrorMessage(ex, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());
        return errorMessage;
    }

/*
   @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    ErrorMessage GeneralHandler(Exception exception) {
        errorMessage= buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());


        return errorMessage;

    }*/
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadAutentication.class)
    ErrorMessage BadParameterHandler(BadAutentication exception) {
        errorMessage= buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());
        return errorMessage;

    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ErrorMessage BadParameterHandler(IllegalArgumentException exception) {
        errorMessage= buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());
        return errorMessage;

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    ErrorMessage BadParameterHandler(EntityExistsException exception) {
        errorMessage= buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());
        return errorMessage;

    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    ErrorMessage BadParameterHandler(EntityNotFoundException exception) {
        errorMessage= buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());
        return errorMessage;

    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    ErrorMessage BadParameterHandler(BadCredentialsException exception) {
        errorMessage= buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
        LOGGER.error(errorMessage.toString());
        return errorMessage;

    }




    private ErrorMessage buildErrorMessage(Exception exception, HttpStatus httpStatus) {
        return new ErrorMessage(UUID.randomUUID().toString(),httpStatus.toString(),exception.getMessage(), new Date());
    }
}