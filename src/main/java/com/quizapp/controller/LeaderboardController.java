package com.quizapp.controller;

import com.quizapp.dto.leaderboard.*;
import com.quizapp.service.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
@Tag(name="Leaderboard APIs")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Operation(summary = "Check Global Leaderboard")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/global")
    public List<LeaderboardResponse>
    getGlobalLeaderboard() {

        return leaderboardService
                .getGlobalLeaderboard();
    }

    @Operation(summary = "Get Quiz Leaderboard")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/quiz/{quizId}")
    public List<LeaderboardResponse>
    getQuizLeaderboard(
            @PathVariable Long quizId) {

        return leaderboardService
                .getQuizLeaderboard(quizId);
    }

    @Operation(summary = "Get Quiz Analytics")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/analytics/{quizId}")
    public QuizAnalyticsResponse
    getQuizAnalytics(
            @PathVariable Long quizId) {

        return leaderboardService
                .getQuizAnalytics(quizId);
    }
}