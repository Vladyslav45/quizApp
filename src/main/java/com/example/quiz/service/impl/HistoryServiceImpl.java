package com.example.quiz.service.impl;

import com.example.quiz.model.HistoryQuiz;
import com.example.quiz.model.User;
import com.example.quiz.repository.HistoryRepository;
import com.example.quiz.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void save(String theme, String result, User user) {
        HistoryQuiz historyQuiz = new HistoryQuiz();
                historyQuiz.setTheme(theme);
                historyQuiz.setResult(result);
                historyQuiz.setUser(user);
        historyRepository.save(historyQuiz);
    }
}
