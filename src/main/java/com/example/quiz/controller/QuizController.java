package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.service.IQuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private IQuizService IQuizService;


    @GetMapping(value = "/{nameSubject}")
    public String showThemeSubjects(@PathVariable String nameSubject, Model model){
        List<ThemeSubject> themeSubjectList = IQuizService.getAllBySubjectName(nameSubject);
        model.addAttribute("themeSubjects", themeSubjectList);
        return "showThemeSubject";
    }

    @GetMapping(value = "/test/{theme}")
    public String showTest(@PathVariable String theme, Model model) {
        List<Question> questions = IQuizService.getAnswersForTest(theme);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(questions);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("questionList", questions);
        model.addAttribute("nameTheme", theme);
        return "quizForm";
    }
}
