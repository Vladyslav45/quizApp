package com.example.quiz.service;

import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;

import java.util.List;

public interface QuizService {
    List<Subject> getAllSubjects();
    List<ThemeSubject> getAllBySubjectName(String subjectName);
    Subject getSubjectByName(String name);
}
