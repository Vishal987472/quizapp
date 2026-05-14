package com.quizapp.service;

import com.quizapp.dto.common.PageResponse;
import com.quizapp.dto.quiz.QuizRequest;
import com.quizapp.dto.quiz.QuizResponse;

import java.util.List;

public interface QuizService {

    QuizResponse createQuiz(QuizRequest request);

    PageResponse<QuizResponse> getAllQuizzes(
            int page,
            int size,
            String sortBy,
            String category,
            String difficulty
    );
    QuizResponse getQuizById(Long id);

    QuizResponse updateQuiz(
            Long id,
            QuizRequest request);

    void deleteQuiz(Long id);
}