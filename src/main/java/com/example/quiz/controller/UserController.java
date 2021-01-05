package com.example.quiz.controller;

import com.example.quiz.model.ConfirmedTokenActivetedEmail;
import com.example.quiz.model.DTO.UserUpdateDTO;
import com.example.quiz.model.Subject;
import com.example.quiz.model.User;
import com.example.quiz.service.IHistoryService;
import com.example.quiz.service.IQuizService;
import com.example.quiz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IQuizService IQuizService;

    @Autowired
    private IHistoryService iHistoryService;

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model){

        if (error != null){
            model.addAttribute("messageErrorLog", "Invalid email or password");
        }
        if (logout != null){
            model.addAttribute("messagelogout", "You've been logged out successfully");
        }
        return "login";
    }

    @GetMapping(value = "/singup")
    public String getFormSignUp(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "singup";
    }

    @PostMapping(value = "/singup")
    public String registeredUser(@ModelAttribute User user, HttpServletRequest request, Model model){
        User createUser = userService.findByEmail(user.getEmail());

        if (createUser != null){
            model.addAttribute("messageError", "Email is registered");
        }

        userService.save(user);
        String appUrl = "test " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        userService.ConfirmedAccount(user, appUrl);
        return "redirect:/";
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String homePage(Model model){
        List<Subject> subjects = IQuizService.getAllSubjects();

        model.addAttribute("userName", getAuthenticationUser().getName());
        model.addAttribute("subjects", subjects);
        return "main/homePage";
    }

    @RequestMapping(value = "/confirmActivated", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmReset(@RequestParam("token") String confirmToken, Model model){
        ConfirmedTokenActivetedEmail confirmedTokenActivetedEmail = userService.findByConfirmToken(confirmToken);
        if (confirmedTokenActivetedEmail != null){
            userService.updateActive(confirmedTokenActivetedEmail);
            model.addAttribute("messageAccess", "Congratulation!");
            return "success";
        } else {
            model.addAttribute("messageError", "The link is invalid or broken");
            return "error";
        }
    }

    @GetMapping(value = "/forgot")
    public String checkEmail(){
        return "emailCheckForm";
    }

    @PostMapping(value = "/forgot")
    public String check(@RequestParam String userEmail, HttpServletRequest request, Model model){
        User user = userService.findByEmail(userEmail);
        if (user == null){
            model.addAttribute("messageError", "Not found email");
        } else {
            String token = UUID.randomUUID().toString();
            userService.updateTokenInUserForResetPassword(user, token);
            String appUrl = "test " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            userService.sendEmailWithResetToken(userEmail, token, appUrl);
            model.addAttribute("successMessage", "Message send to " + userEmail);
        }
        return "emailCheckForm";
    }


    @GetMapping(value = "/reset")
    public String resetPasswordForm(@RequestParam("token") String token, Model model){
        User user = userService.findByResetToken(token);
        if (user != null){
            model.addAttribute("mess", "congre");
            model.addAttribute("resetToken", token);
            return "changePasswordForm";
        } else {
            model.addAttribute("me", "The link is invalid");
            return "error";
        }
    }

    @PostMapping(value = "/reset")
    public String resetPassword(@RequestParam String password, @RequestParam("token") String token){
        userService.changePassword(password, userService.findByResetToken(token));
        return "redirect:/login";
    }

    @GetMapping(value = "/profile")
    public String profile(Model model){

        model.addAttribute("user", getAuthenticationUser());
        return "user/profile";
    }

    @PostMapping(value = "/saveHistory")
    public String save(@RequestParam(value = "theme") String theme, @RequestParam(value = "result") String result){
        iHistoryService.save(theme, result, getAuthenticationUser());
        return "quizForm :: frag";
    }


    @GetMapping(value = "/update/{userId}")
    public String update(@PathVariable("userId") Long userId, Model model){
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping(value = "/update/{userId}")
    public String update(@PathVariable("userId") Long userId, @ModelAttribute UserUpdateDTO user){
        userService.updateData(userId,user);
        return "redirect:/profile";
    }

    private User getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(authentication.getName());
    }
}
