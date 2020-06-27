package com.example.quiz.service.impl;

import com.example.quiz.model.User;
import com.example.quiz.repository.QuizAnswersRepository;
import com.example.quiz.repository.QuizCategoryRepository;
import com.example.quiz.repository.UserRepository;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizCategoryRepository quizCategoryRepository;

    @Autowired
    private QuizAnswersRepository quizAnswersRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
}
