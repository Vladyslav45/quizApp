package com.example.quiz.controller;

import com.example.quiz.model.User;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping(value = {"/","login"})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model){

        if (error != null){
            model.addAttribute("message", "Invalid email or password");
        }
        if (logout != null){
            model.addAttribute("messagelogout", "You've been logged out successfully");
        }
        return "login";
    }

    @GetMapping(value = "singup")
    public String getFormSignUp(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "singup";
    }

    @PostMapping(value = "singup")
    public String registeredUser(@Valid @ModelAttribute User user, Model model){
        User createUser = quizService.findByEmail(user.getEmail());

        if (createUser.getEmail() != null){
            model.addAttribute("messageError", "Email is registered");
        }

        quizService.save(user);
        return "redirect:/home";
    }
}
