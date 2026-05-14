package com.quizapp.repository;

import com.quizapp.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizAttemptRepository
        extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByUserId(Long userId);

    @Query("""
    SELECT ua.user.username,
           SUM(ua.score),
           AVG(ua.percentage),
           COUNT(ua)
    FROM QuizAttempt ua
    GROUP BY ua.user.username
    ORDER BY SUM(ua.score) DESC
""")

    List<Object[]> getGlobalLeaderboard();

    @Query("""
    SELECT qa.user.username,
           qa.score,
           qa.percentage
    FROM QuizAttempt qa
    WHERE qa.quiz.id = :quizId
    ORDER BY qa.score DESC
""")

    List<Object[]> getQuizLeaderboard(Long quizId);

    @Query("""
    SELECT COUNT(qa),
           AVG(qa.percentage),
           MAX(qa.percentage),
           MIN(qa.percentage)
    FROM QuizAttempt qa
    WHERE qa.quiz.id = :quizId
""")

    List<Object[]> getQuizAnalytics(Long quizId);
}