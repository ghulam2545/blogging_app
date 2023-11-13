package com.ghulam.exceptions;

import com.ghulam.response.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    private ResponseEntity<Object> response(String message, Object data) {
        return Response.builder(message, HttpStatus.BAD_REQUEST, data);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> userNotFound(CategoryNotFoundException ex) {
        String msg = ex.getMessage();
        return response(msg, null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UserNotFoundException ex) {
        String msg = ex.getMessage();
        return response(msg, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> duplicateEntry(DataIntegrityViolationException ex) {
        var msg = ex.getMessage();
        return response(msg, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> invalidEntry(MethodArgumentNotValidException ex) {
        Map<String, String> result = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            var fieldName = ((FieldError) err).getField();
            var msg = err.getDefaultMessage();

            result.put(fieldName, msg);
        });
        return response("Invalid input entry", result);
    }
}
