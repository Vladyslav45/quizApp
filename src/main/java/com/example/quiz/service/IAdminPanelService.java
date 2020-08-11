package com.example.quiz.service;

import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import org.springframework.web.multipart.MultipartFile;

public interface IAdminPanelService {
    void addSubject(Subject subject);
    void addThemeSubject(ThemeSubject themeSubject);
    void addAnswersForTHemeSubject(Question question);
    void addListAnswers(MultipartFile multipartFile);
//    void addListSubjects(MultipartFile file);
}
