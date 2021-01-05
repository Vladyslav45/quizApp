package com.example.quiz.repository;

import com.example.quiz.model.HistoryQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryQuiz, Long> {
}
