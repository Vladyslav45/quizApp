package com.example.quiz.service;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAdminPanelService {
    void addSubject(Subject subject);
    void addThemeSubject(ThemeSubject themeSubject);
    void addAnswersForTHemeSubject(Answers answers);
    void addListAnswers(MultipartFile multipartFile);
//    void addListSubjects(MultipartFile file);
}
