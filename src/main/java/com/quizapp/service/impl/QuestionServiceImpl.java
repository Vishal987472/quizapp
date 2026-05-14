package com.quizapp.service.impl;

import com.quizapp.dto.question.*;
import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;
import com.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class QuestionServiceImpl
        implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public QuestionResponse createQuestion(
            QuestionRequest request) {

        Quiz quiz = quizRepository.findById(
                        request.getQuizId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        Question question = Question.builder()
                .questionText(request.getQuestionText())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .optionC(request.getOptionC())
                .optionD(request.getOptionD())
                .correctAnswer(
                        request.getCorrectAnswer()
                )
                .quiz(quiz)
                .build();

        questionRepository.save(question);

        return mapToResponse(question);
    }

    @Override
    public List<QuestionResponse>
    getQuestionsByQuiz(Long quizId) {

        return questionRepository
                .findByQuizId(quizId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public QuestionResponse updateQuestion(
            Long id,
            QuestionRequest request) {

        Question question =
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Question not found"));

        Quiz quiz = quizRepository.findById(
                        request.getQuizId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        question.setQuestionText(
                request.getQuestionText());

        question.setOptionA(request.getOptionA());
        question.setOptionB(request.getOptionB());
        question.setOptionC(request.getOptionC());
        question.setOptionD(request.getOptionD());

        question.setCorrectAnswer(
                request.getCorrectAnswer());

        question.setQuiz(quiz);

        questionRepository.save(question);

        return mapToResponse(question);
    }

    @Override
    public void deleteQuestion(Long id) {

        questionRepository.deleteById(id);
    }

    private QuestionResponse mapToResponse(
            Question question) {

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(
                        question.getQuestionText())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .correctAnswer(
                        question.getCorrectAnswer())
                .quizId(
                        question.getQuiz().getId())
                .build();
    }
}