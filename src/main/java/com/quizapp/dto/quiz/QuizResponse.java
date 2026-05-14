package com.quizapp.dto.quiz;

import com.quizapp.enums.DifficultyLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class QuizResponse {

    private Long id;

    private String title;

    private String description;

    private String category;

    private DifficultyLevel difficultyLevel;

    private Integer timeLimit;

    private Boolean published;
}