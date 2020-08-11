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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    //TODO make good random show questions
    @Override
    public List<Question> getRandomThreeTestsByThemeSubject(String themeSubject) {
        List<Question> getTests = new ArrayList<>();
        List<Question> questions = questionRepository.findAllQuestion(themeSubject);
        while (getTests.size() != 2){
            Question question = getRandomQuestion(questions);
            if (getTests.stream().noneMatch(question::equals)){
                getTests.add(question);
            }
        }
        return getTests;
    }

    private Question getRandomQuestion(List<Question> questions){
        return questions.get(ThreadLocalRandom.current().nextInt(questions.size()) % questions.size());
    }
}
