package com.example.quiz.repository;

import com.example.quiz.model.ThemeSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeSubjectsRepository extends JpaRepository<ThemeSubject, Long> {
    List<ThemeSubject> findAllBySubject_NameSubject(String nameSubject);
    ThemeSubject findByName(String name);
}
