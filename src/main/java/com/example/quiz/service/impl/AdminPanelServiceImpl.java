package com.example.quiz.service.impl;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.repository.AnswersRepository;
import com.example.quiz.repository.SubjectRepository;
import com.example.quiz.repository.ThemeSubjectsRepository;
import com.example.quiz.service.IAdminPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
public class AdminPanelServiceImpl implements IAdminPanelService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ThemeSubjectsRepository themeSubjectsRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void addThemeSubject(ThemeSubject themeSubject) {
        themeSubjectsRepository.save(themeSubject);
    }

    @Override
    public void addAnswersForTHemeSubject(Answers answers) {
        answersRepository.save(answers);
    }

    @Override
    public void addListAnswerForThemeSubject(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                String[] strings = line.split(";");
                Answers answers = make(strings);
                answersRepository.save(answers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Answers make(String[] strings){
        Answers answers = new Answers();
        answers.setName(strings[0].trim());
        answers.setCorrectAnswer(strings[1].trim());
        answers.setAnswers1(strings[2].trim());
        answers.setAnswer2(strings[3].trim());
        answers.setAnswer3(strings[4].trim());
        answers.setThemeSubject(themeSubjectsRepository.findByName(strings[5].trim()));
        return answers;
    }

//    @Override
//    public void addListSubjects(MultipartFile file) {
//    }
}
