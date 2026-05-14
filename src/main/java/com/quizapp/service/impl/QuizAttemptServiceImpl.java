package com.quizapp.service.impl;

import com.quizapp.dto.attempt.*;
import com.quizapp.entity.*;
import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.repository.*;
import com.quizapp.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class QuizAttemptServiceImpl
        implements QuizAttemptService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository attemptRepository;
    private final UserAnswerRepository answerRepository;

    @Override
    public QuizResultResponse submitQuiz(
            String userEmail,
            QuizSubmissionRequest request) {

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        Quiz quiz = quizRepository
                .findById(request.getQuizId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        int correct = 0;
        int wrong = 0;

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .user(user)
                .submittedAt(LocalDateTime.now())
                .build();

        attemptRepository.save(attempt);

        for(AnswerRequest answerRequest
                : request.getAnswers()) {

            Question question =
                    questionRepository.findById(
                                    answerRequest.getQuestionId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Question not found"));

            boolean isCorrect =
                    question.getCorrectAnswer()
                            .equalsIgnoreCase(
                                    answerRequest
                                            .getSelectedAnswer()
                            );

            if(isCorrect) {
                correct++;
            } else {
                wrong++;
            }

            UserAnswer answer = UserAnswer.builder()
                    .attempt(attempt)
                    .question(question)
                    .selectedAnswer(
                            answerRequest
                                    .getSelectedAnswer())
                    .correct(isCorrect)
                    .build();

            answerRepository.save(answer);
        }

        int total =
                correct + wrong;

        double percentage =
                ((double) correct / total) * 100;

        attempt.setScore(correct);
        attempt.setCorrectAnswers(correct);
        attempt.setWrongAnswers(wrong);
        attempt.setTotalQuestions(total);
        attempt.setPercentage(percentage);

        attemptRepository.save(attempt);

        return QuizResultResponse.builder()
                .score(correct)
                .correctAnswers(correct)
                .wrongAnswers(wrong)
                .totalQuestions(total)
                .percentage(percentage)
                .build();
    }

    @Override
    public List<QuizResultResponse>
    getUserAttempts(String userEmail) {

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        return attemptRepository
                .findByUserId(user.getId())
                .stream()
                .map(attempt ->
                        QuizResultResponse.builder()
                                .score(attempt.getScore())
                                .correctAnswers(
                                        attempt.getCorrectAnswers())
                                .wrongAnswers(
                                        attempt.getWrongAnswers())
                                .totalQuestions(
                                        attempt.getTotalQuestions())
                                .percentage(
                                        attempt.getPercentage())
                                .build()
                )
                .toList();
    }
}