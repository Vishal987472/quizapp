package com.quizapp.service;

import com.quizapp.dto.question.*;

import java.util.List;

public interface QuestionService {

    QuestionResponse createQuestion(
            QuestionRequest request);

    List<QuestionResponse> getQuestionsByQuiz(
            Long quizId);

    QuestionResponse updateQuestion(
            Long id,
            QuestionRequest request);

    void deleteQuestion(Long id);
}