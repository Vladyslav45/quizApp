package com.example.quiz.controller;

import com.example.quiz.model.ThemeSubject;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping(value = "/{nameSubject}")
    public String showThemeSubjects(@PathVariable String nameSubject, Model model){
        List<ThemeSubject> themeSubjectList = quizService.getAllBySubjectName(quizService.getSubjectByName(nameSubject).getNameSubject());
        model.addAttribute("themeSubjects", themeSubjectList);
        return "showThemeSubject";
    }

}
