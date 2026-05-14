package com.quizapp.entity;

import com.quizapp.enums.DifficultyLevel;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "quizzes")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    private Integer timeLimit;

    private Boolean published;

    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "quiz",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Question> questions;
}