package com.example.quiz.service;

import com.example.quiz.model.HistoryQuiz;
import com.example.quiz.model.User;

import java.util.Map;

public interface IHistoryService {
    void save(String theme, String result, User user);
}
