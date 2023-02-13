package com.example.kameleoon.advice;

import com.example.kameleoon.dto.ApiError;
import com.example.kameleoon.exception.IllegalQuoteStateException;
import com.example.kameleoon.exception.QuoteNotFoundException;
import com.example.kameleoon.exception.UserAlreadyExistException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return createResponseEntity(HttpStatus.BAD_REQUEST, (HttpServletRequest) request, errors);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExists(RuntimeException ex, HttpServletRequest httpRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return createResponseEntity(HttpStatus.CONFLICT, httpRequest, errors);
    }

    @ExceptionHandler({QuoteNotFoundException.class, IllegalQuoteStateException.class})
    public ResponseEntity<Object> handleQuoteException(RuntimeException ex, HttpServletRequest httpRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return createResponseEntity(HttpStatus.BAD_REQUEST, httpRequest, errors);
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatus status,
                                                        HttpServletRequest request, Map<String, String> errors) {
        return new ResponseEntity<>(new ApiError(status.toString(), request.getRequestURI(), errors), status);
    }
}
