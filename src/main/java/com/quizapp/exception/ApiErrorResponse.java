package com.quizapp.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class ApiErrorResponse {

    private Boolean success;

    private String message;

    private Integer status;

    private LocalDateTime timestamp;
}