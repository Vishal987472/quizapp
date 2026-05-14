package com.quizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )

    public ResponseEntity<ApiErrorResponse>
    handleResourceNotFound(
            ResourceNotFoundException ex) {

        ApiErrorResponse response =
                ApiErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .status(404)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(
            BadRequestException.class
    )

    public ResponseEntity<ApiErrorResponse>
    handleBadRequest(
            BadRequestException ex) {

        ApiErrorResponse response =
                ApiErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .status(400)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )

    public ResponseEntity<ApiErrorResponse>
    handleValidation(
            MethodArgumentNotValidException ex) {

        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error ->
                                error.getField()
                                        + ": "
                                        + error.getDefaultMessage())
                        .collect(Collectors.joining(", "));

        ApiErrorResponse response =
                ApiErrorResponse.builder()
                        .success(false)
                        .message(message)
                        .status(400)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ApiErrorResponse>
    handleGenericException(
            Exception ex) {

        ApiErrorResponse response =
                ApiErrorResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .status(500)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}