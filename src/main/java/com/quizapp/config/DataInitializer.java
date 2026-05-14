package com.quizapp.config;

import com.quizapp.entity.Quiz;
import com.quizapp.enums.DifficultyLevel;
import com.quizapp.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final QuizRepository quizRepository;

    @Override
    public void run(String... args) {

        if (quizRepository.count() == 0) {

            List<Quiz> quizzes = List.of(

                    Quiz.builder()
                            .title("Spring Boot Basics")
                            .description("Core Spring Boot Concepts")
                            .category("Backend")
                            .difficultyLevel(DifficultyLevel.valueOf("EASY"))
                            .timeLimit(30)
                            .published(true)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    Quiz.builder()
                            .title("Java Advanced")
                            .description("Advanced Java Quiz")
                            .category("Programming")
                            .difficultyLevel(DifficultyLevel.valueOf("HARD"))
                            .timeLimit(45)
                            .published(true)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    Quiz.builder()
                            .title("PostgreSQL Mastery")
                            .description("Database Concepts")
                            .category("Database")
                            .difficultyLevel(DifficultyLevel.valueOf("MEDIUM"))
                            .timeLimit(40)
                            .published(true)
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            quizRepository.saveAll(quizzes);

            System.out.println(
                    "Sample quizzes inserted successfully!"
            );
        }
    }
}