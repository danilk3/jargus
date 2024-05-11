package org.jargus.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Kotelnikov D.M.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<String> httpClientErrorHandler(HttpServletRequest request, HttpClientErrorException e) {
        return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<String> defaultErrorHandler(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}