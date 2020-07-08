package com.example.quiz.service;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuizService {
    List<Subject> getAllSubjects();
    List<ThemeSubject> getAllBySubjectName(String subjectName);
    List<Question> getRandomThreeTestsByThemeSubject(String themeSubject);
    int findById(HttpServletRequest request);
}
