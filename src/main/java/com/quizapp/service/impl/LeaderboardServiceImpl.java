package com.quizapp.service.impl;

import com.quizapp.dto.leaderboard.*;
import com.quizapp.entity.Quiz;
import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.repository.*;
import com.quizapp.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LeaderboardServiceImpl
        implements LeaderboardService {

    private final QuizAttemptRepository attemptRepository;
    private final QuizRepository quizRepository;

    @Override
    public List<LeaderboardResponse>
    getGlobalLeaderboard() {

        return attemptRepository
                .getGlobalLeaderboard()
                .stream()
                .map(obj ->
                        LeaderboardResponse.builder()
                                .username((String) obj[0])
                                .totalScore(
                                        ((Number) obj[1]).intValue())
                                .averagePercentage(
                                        ((Number) obj[2]).doubleValue())
                                .quizzesAttempted(
                                        ((Number) obj[3]).longValue())
                                .build()
                )
                .toList();
    }

    @Override
    public List<LeaderboardResponse>
    getQuizLeaderboard(Long quizId) {

        return attemptRepository
                .getQuizLeaderboard(quizId)
                .stream()
                .map(obj ->
                        LeaderboardResponse.builder()
                                .username((String) obj[0])
                                .totalScore(
                                        ((Number) obj[1]).intValue())
                                .averagePercentage(
                                        ((Number) obj[2]).doubleValue())
                                .build()
                )
                .toList();
    }

    @Override
    public QuizAnalyticsResponse
    getQuizAnalytics(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        Object[] analytics =
                attemptRepository
                        .getQuizAnalytics(quizId)
                        .get(0);

        return QuizAnalyticsResponse.builder()
                .quizId(quiz.getId())
                .quizTitle(quiz.getTitle())
                .totalAttempts(
                        ((Number) analytics[0]).longValue())
                .averageScore(
                        analytics[1] != null
                                ? ((Number) analytics[1]).doubleValue()
                                : 0
                )
                .highestScore(
                        analytics[2] != null
                                ? ((Number) analytics[2]).doubleValue()
                                : 0
                )
                .lowestScore(
                        analytics[3] != null
                                ? ((Number) analytics[3]).doubleValue()
                                : 0
                )
                .build();
    }
}