package com.quizapp.controller;

import com.quizapp.dto.question.*;
import com.quizapp.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Tag(name = "Question APIs")
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "Create new Question")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public QuestionResponse createQuestion(
            @Valid @RequestBody
            QuestionRequest request) {

        return questionService
                .createQuestion(request);
    }

    @Operation(summary = "Get all Questions with quizId")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/quiz/{quizId}")
    public List<QuestionResponse>
    getQuestionsByQuiz(
            @PathVariable Long quizId) {

        return questionService
                .getQuestionsByQuiz(quizId);
    }

    @Operation(summary = "Update existing Question")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public QuestionResponse updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody
            QuestionRequest request) {

        return questionService
                .updateQuestion(id, request);
    }

    @Operation(summary = "Delete existing Question")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);
    }
}