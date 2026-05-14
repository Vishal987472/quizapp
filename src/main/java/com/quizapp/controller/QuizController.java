package com.quizapp.controller;

import com.quizapp.dto.common.PageResponse;
import com.quizapp.dto.quiz.*;
import com.quizapp.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Tag(name = "Quiz APIs")
public class QuizController {

    private final QuizService quizService;

    @Operation(summary = "Create new Quiz")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public QuizResponse createQuiz(
            @Valid @RequestBody
            QuizRequest request) {

        return quizService.createQuiz(request);
    }

    @Operation(summary = "Get all quizzes with pagination")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public PageResponse<QuizResponse>
    getAllQuizzes(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(required = false)
            String category,

            @RequestParam(required = false)
            String difficulty
    ) {

        return quizService.getAllQuizzes(
                page,
                size,
                sortBy,
                category,
                difficulty
        );
    }

    @Operation(summary = "Get Quiz by Id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public QuizResponse getQuizById(
            @PathVariable Long id) {

        return quizService.getQuizById(id);
    }

    @Operation(summary = "Update Existing Quiz")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public QuizResponse updateQuiz(
            @PathVariable Long id,
            @Valid @RequestBody
            QuizRequest request) {

        return quizService.updateQuiz(id, request);
    }

    @Operation(summary = "Delete existing Quiz")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteQuiz(
            @PathVariable Long id) {

        quizService.deleteQuiz(id);
    }
}