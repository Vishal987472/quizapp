package com.quizapp.service;

import com.quizapp.dto.leaderboard.*;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardResponse>
    getGlobalLeaderboard();

    List<LeaderboardResponse>
    getQuizLeaderboard(Long quizId);

    QuizAnalyticsResponse
    getQuizAnalytics(Long quizId);
}