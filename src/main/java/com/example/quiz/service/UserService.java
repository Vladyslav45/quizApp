package com.example.quiz.service;

import com.example.quiz.model.User;

public interface UserService {
    void save(User user);
    User findByEmail(String email);
}
