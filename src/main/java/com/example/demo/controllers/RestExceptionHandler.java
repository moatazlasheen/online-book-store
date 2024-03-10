package com.example.demo.controllers;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.UnavailableBookException;
import com.example.demo.integration.models.APIError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    protected ResponseEntity<APIError> onBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        ResponseEntity<APIError> responseEntity = ResponseEntity.notFound().build();
        return responseEntity;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> onMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        final APIError apiError = new APIError(LocalDateTime.now(), 400, "Bad Request",
                ex.getFieldErrors().get(0).getField() + " " +
                        ex.getFieldErrors().get(0).getDefaultMessage(), request.getDescription(false));
        ResponseEntity<APIError> responseEntity = ResponseEntity.badRequest().body(apiError);
        return responseEntity;
    }

    @ExceptionHandler(UnavailableBookException.class)
    protected ResponseEntity<?> onUnavailableBookException(UnavailableBookException ex, WebRequest request) {
        final APIError apiError = new APIError(LocalDateTime.now(), 400, "Bad Request",
                "the book is not available for borrowing", request.getDescription(false));
        ResponseEntity<APIError> responseEntity = ResponseEntity.badRequest().body(apiError);
        return responseEntity;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    protected ResponseEntity<?> onCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        final APIError apiError = new APIError(LocalDateTime.now(), 400, "Bad Request",
                "category was not found", request.getDescription(false));
        ResponseEntity<APIError> responseEntity = ResponseEntity.badRequest().body(apiError);
        return responseEntity;
    }
}
