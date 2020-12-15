package com.example.quiz.service.impl;

import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.SubjectRepository;
import com.example.quiz.repository.ThemeSubjectsRepository;
import com.example.quiz.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements IQuizService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ThemeSubjectsRepository themeSubjectsRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<ThemeSubject> getAllBySubjectName(String subjectName) {
        return themeSubjectsRepository.findAllBySubject_NameSubject(subjectName);
    }

    @Override
    public List<Question> getAnswersForTest(String themeSubject) {
        List<Question> questions = questionRepository.findAllQuestion(themeSubject);
        Collections.shuffle(questions, new Random());
        return questions.stream().limit(4).collect(Collectors.toList());
    }
}
