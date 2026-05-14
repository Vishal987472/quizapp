package com.quizapp.service.impl;

import com.quizapp.dto.quiz.*;
import com.quizapp.entity.Quiz;
import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.repository.QuizRepository;
import com.quizapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.quizapp.dto.common.PageResponse;
import com.quizapp.enums.DifficultyLevel;
import org.springframework.data.domain.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public QuizResponse createQuiz(
            QuizRequest request) {

        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .difficultyLevel(
                        request.getDifficultyLevel()
                )
                .timeLimit(request.getTimeLimit())
                .published(false)
                .createdAt(LocalDateTime.now())
                .build();

        quizRepository.save(quiz);

        return mapToResponse(quiz);
    }

    @Override
    public PageResponse<QuizResponse> getAllQuizzes(
            int page,
            int size,
            String sortBy,
            String category,
            String difficulty
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        Page<Quiz> quizPage;

        if(category != null && difficulty != null) {

            quizPage =
                    quizRepository
                            .findByCategoryAndDifficultyLevel(
                                    category,
                                    DifficultyLevel.valueOf(
                                            difficulty.toUpperCase()
                                    ),
                                    pageable
                            );

        } else if(category != null) {

            quizPage =
                    quizRepository.findByCategory(
                            category,
                            pageable
                    );

        } else if(difficulty != null) {

            quizPage =
                    quizRepository.findByDifficultyLevel(
                            DifficultyLevel.valueOf(
                                    difficulty.toUpperCase()
                            ),
                            pageable
                    );

        } else {

            quizPage =
                    quizRepository.findAll(pageable);
        }

        List<QuizResponse> quizzes =
                quizPage.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return PageResponse.<QuizResponse>builder()
                .content(quizzes)
                .page(quizPage.getNumber())
                .size(quizPage.getSize())
                .totalElements(
                        quizPage.getTotalElements()
                )
                .totalPages(
                        quizPage.getTotalPages()
                )
                .last(quizPage.isLast())
                .build();
    }

    @Override
    public QuizResponse getQuizById(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        return mapToResponse(quiz);
    }

    @Override
    public QuizResponse updateQuiz(
            Long id,
            QuizRequest request) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(request.getCategory());
        quiz.setDifficultyLevel(
                request.getDifficultyLevel()
        );
        quiz.setTimeLimit(request.getTimeLimit());

        quizRepository.save(quiz);

        return mapToResponse(quiz);
    }

    @Override
    public void deleteQuiz(Long id) {

        quizRepository.deleteById(id);
    }

    private QuizResponse mapToResponse(
            Quiz quiz) {

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .category(quiz.getCategory())
                .difficultyLevel(
                        quiz.getDifficultyLevel()
                )
                .timeLimit(quiz.getTimeLimit())
                .published(quiz.getPublished())
                .build();
    }

}