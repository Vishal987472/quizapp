package com.quizapp.dto.leaderboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LeaderboardResponse {

    private String username;

    private Integer totalScore;

    private Double averagePercentage;

    private Long quizzesAttempted;
}