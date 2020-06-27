package com.example.quiz.service;

import com.example.quiz.model.User;

public interface QuizService {
    void save(User user);
    User findByEmail(String email);
}
