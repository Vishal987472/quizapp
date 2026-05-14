package com.quizapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_answers")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String selectedAnswer;

    private Boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id")
    private QuizAttempt attempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}