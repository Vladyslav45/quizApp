package com.example.quiz.service.impl;

import com.example.quiz.model.Subject;
import com.example.quiz.repository.AnswersRepository;
import com.example.quiz.repository.SubjectRepository;
import com.example.quiz.repository.ThemeSubjectsRepository;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @Autowired
    private ThemeSubjectsRepository themeSubjectsRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
