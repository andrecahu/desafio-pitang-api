package com.pitang.desafiopitangapi.infra;

import com.pitang.desafiopitangapi.exceptions.InvalidTokenException;
import jakarta.persistence.EntityNotFoundException;
import com.pitang.desafiopitangapi.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundHandler (EntityNotFoundException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(threatResponse.getStatus()).body(threatResponse);
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<RestErrorMessage> businessExceptionHandler (BusinessException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(exception.getMessage(), exception.getStatus());
        return ResponseEntity.status(exception.getStatus()).body(threatResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessage> runtimeExceptionHandler (RuntimeException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(threatResponse.getStatus()).body(threatResponse);
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<RestErrorMessage> runtimeExceptionHandler (InvalidTokenException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(threatResponse.getStatus()).body(threatResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<RestErrorMessage> badCredentialsExceptionHandler (Exception exception){
        RestErrorMessage threatResponse = new RestErrorMessage(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(threatResponse.getStatus()).body(threatResponse);
    }


}
