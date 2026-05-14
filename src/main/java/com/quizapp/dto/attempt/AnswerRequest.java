package com.quizapp.dto.attempt;

import lombok.Data;

@Data

public class AnswerRequest {

    private Long questionId;

    private String selectedAnswer;
}