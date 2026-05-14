package com.quizapp.controller;

import com.quizapp.dto.attempt.*;
import com.quizapp.service.QuizAttemptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
@Tag(name="Attempts APIs")
public class QuizAttemptController {

    private final QuizAttemptService attemptService;

    @Operation(summary = "Get Quiz Result")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit")
    public QuizResultResponse submitQuiz(
            @RequestBody
            QuizSubmissionRequest request,

            Authentication authentication) {

        return attemptService.submitQuiz(
                authentication.getName(),
                request
        );
    }

    @Operation(summary = "Get Quiz Attempt History")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/history")
    public List<QuizResultResponse>
    getHistory(Authentication authentication) {

        return attemptService.getUserAttempts(
                authentication.getName()
        );
    }
}