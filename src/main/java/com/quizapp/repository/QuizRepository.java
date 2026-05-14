package com.quizapp.repository;

import com.quizapp.entity.Quiz;
import com.quizapp.enums.DifficultyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository
        extends JpaRepository<Quiz, Long> {

    Page<Quiz> findByCategory(
            String category,
            Pageable pageable
    );

    Page<Quiz> findByDifficultyLevel(
            DifficultyLevel difficultyLevel,
            Pageable pageable
    );

    Page<Quiz> findByCategoryAndDifficultyLevel(
            String category,
            DifficultyLevel difficultyLevel,
            Pageable pageable
    );
}