package com.example.quiz.repository;

import com.example.quiz.model.ThemeSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeSubjectsRepository extends JpaRepository<ThemeSubject, Long> {
}
