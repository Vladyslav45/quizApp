package com.example.quiz.service;

import com.example.quiz.model.ConfirmedTokenActivetedEmail;
import com.example.quiz.model.User;

import javax.servlet.http.HttpServletRequest;
public interface IUserService {
    void save(User user);
    User findByEmail(String email);
    User findByResetToken(String token);
    void changePassword(String pass, User user);
    void ConfirmedAccount(User user, HttpServletRequest request);
    ConfirmedTokenActivetedEmail findByConfirmToken(String confirmToken);
    void updateActive(ConfirmedTokenActivetedEmail confirmedTokenActivetedEmail);
    void updateTokenInUserForResetPassword(User user, String token);
    void sendEmailWithResetToken(String userEmail, String resetToken, HttpServletRequest request);
}
