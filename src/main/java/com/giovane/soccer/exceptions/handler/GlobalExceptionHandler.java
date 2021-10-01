package com.giovane.soccer.exceptions.handler;

import com.giovane.soccer.exceptions.details.ExceptionDetails;
import com.giovane.soccer.exceptions.details.MethodNotValidDetails;
import com.giovane.soccer.exceptions.notfound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(NotFoundException e){
        ExceptionDetails exceptionDetails;
        exceptionDetails = ExceptionDetails.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not found")
                .timestamp(Instant.now())
                .details(e.getMessage())
                .developerMessage("Include a valid ID. Make sure it exists.")
                .build();
        return exceptionDetails;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MethodNotValidDetails handlerMethodNotValid(MethodArgumentNotValidException e){
        Map<String, String> error = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(p -> error.put(p.getField(), p.getDefaultMessage()));

        MethodNotValidDetails methodNotValidDetails;
        methodNotValidDetails = MethodNotValidDetails.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Body contains invalid JSON")
                .timestamp(Instant.now())
                .details(error)
                .developerMessage("Error! Check the number of characters allowed.")
                .build();
        return methodNotValidDetails;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDetails handlerBadRequest(Exception e){
        ExceptionDetails exceptionDetails;
        exceptionDetails = ExceptionDetails.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad request")
                .timestamp(Instant.now())
                .details("You sent a request that this server didn't understand")
                .developerMessage("Check the request")
                .build();
        return exceptionDetails;
    }

}
