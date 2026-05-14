package com.quizapp.dto.leaderboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class QuizAnalyticsResponse {

    private Long quizId;

    private String quizTitle;

    private Long totalAttempts;

    private Double averageScore;

    private Double highestScore;

    private Double lowestScore;
}