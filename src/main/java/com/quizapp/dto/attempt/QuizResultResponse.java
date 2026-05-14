package com.quizapp.dto.attempt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class QuizResultResponse {

    private Integer score;

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Double percentage;
}