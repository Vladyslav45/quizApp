package com.example.quiz.controller;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.service.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;


    @GetMapping(value = "/{nameSubject}")
    public String showThemeSubjects(@PathVariable String nameSubject, Model model){
        List<ThemeSubject> themeSubjectList = quizService.getAllBySubjectName(nameSubject);
        model.addAttribute("themeSubjects", themeSubjectList);
        return "showThemeSubject";
    }

    @GetMapping(value = "/testing/{theme}")
    public String showTest(@PathVariable String theme, Model model) {
        List<Question> questions = quizService.getRandomThreeTestsByThemeSubject(theme);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            model.addAttribute("quests", objectMapper.writeValueAsString(questions));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        model.addAttribute("questionList", questions);
        return "showQuestion";
    }

    @PostMapping(value = "/result")
    public String resultTest(HttpServletRequest request, Model model){
        int result = quizService.findById(request);
        model.addAttribute("result", result);
        return "showResult";
    }
}
