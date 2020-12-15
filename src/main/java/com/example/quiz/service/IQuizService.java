package com.example.quiz.service;

import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;

import java.util.List;

public interface IQuizService {
    List<Subject> getAllSubjects();
    List<ThemeSubject> getAllBySubjectName(String subjectName);
    List<Question> getAnswersForTest(String themeSubject);
}
