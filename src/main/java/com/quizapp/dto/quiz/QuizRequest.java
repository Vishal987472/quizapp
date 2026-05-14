package com.quizapp.dto.quiz;

import com.quizapp.enums.DifficultyLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class QuizRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String category;

    @NotNull
    private DifficultyLevel difficultyLevel;

    private Integer timeLimit;
}