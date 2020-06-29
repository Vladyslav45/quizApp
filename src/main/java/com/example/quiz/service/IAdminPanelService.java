package com.example.quiz.service;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import org.springframework.web.multipart.MultipartFile;

public interface IAdminPanelService {
    void addSubject(Subject subject);
    void addThemeSubject(ThemeSubject themeSubject);
    void addAnswersForTHemeSubject(Answers answers);
    void addListAnswerForThemeSubject(MultipartFile multipartFile);
//    void addListSubjects(MultipartFile file);
}
