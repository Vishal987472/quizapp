package com.quizapp.service;

import com.quizapp.dto.attempt.*;

import java.util.List;

public interface QuizAttemptService {

    QuizResultResponse submitQuiz(
            String userEmail,
            QuizSubmissionRequest request);

    List<QuizResultResponse> getUserAttempts(
            String userEmail);
}