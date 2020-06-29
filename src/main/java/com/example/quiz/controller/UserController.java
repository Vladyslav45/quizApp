package com.example.quiz.controller;

import com.example.quiz.model.Subject;
import com.example.quiz.model.User;
import com.example.quiz.service.QuizService;
import com.example.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
    public String registeredUser(@ModelAttribute User user, Model model){
        User createUser = userService.findByEmail(user.getEmail());

        if (createUser != null){
            model.addAttribute("messageError", "Email is registered");
        }

        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/home")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String homePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        List<Subject> subjects = quizService.getAllSubjects();

        model.addAttribute("userName", user.getName());
        model.addAttribute("subjects", subjects);
        return "main/homePage";
    }
}
