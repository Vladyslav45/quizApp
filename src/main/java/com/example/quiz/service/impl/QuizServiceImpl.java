package com.example.quiz.service.impl;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.repository.AnswersRepository;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.SubjectRepository;
import com.example.quiz.repository.ThemeSubjectsRepository;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AnswersRepository answersRepository;

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
    public List<Question> getRandomThreeTestsByThemeSubject(String themeSubject) {
        List<Question> getTests = new ArrayList<>();
        List<Question> questions = questionRepository.findAllByThemeSubject_Name(themeSubject);
        while (getTests.size() != 3){
            Question question = getRandomQuestion(questions);
            if (getTests.stream().noneMatch(question::equals)){
                getTests.add(question);
            }
        }
        return getTests;
    }

    @Override
    public int findById(HttpServletRequest request) {
        AtomicInteger result = new AtomicInteger();
        String[] ids = request.getParameterValues("questionId");
        Arrays.stream(ids).forEach(question -> questionRepository.findById(Long.parseLong(question)).ifPresent(questionAnswer -> {
            if (questionAnswer.getAnswers().getCorrectAnswer().equals(request.getParameter("question_" + question))){
            result.getAndIncrement();
            }
        }));
        return result.get();
    }

    private Question getRandomQuestion(List<Question> questions){
        return questions.get(ThreadLocalRandom.current().nextInt(questions.size()) % questions.size());
    }
}
