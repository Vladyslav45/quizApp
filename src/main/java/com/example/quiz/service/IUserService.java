package com.example.quiz.service;

import com.example.quiz.model.ConfirmedTokenActivetedEmail;
import com.example.quiz.model.DTO.UserUpdateDTO;
import com.example.quiz.model.User;

public interface IUserService {
    void save(User user);
    User findByEmail(String email);
    User findByResetToken(String token);
    void changePassword(String pass, User user);
    void ConfirmedAccount(User user, String url);
    ConfirmedTokenActivetedEmail findByConfirmToken(String confirmToken);
    void updateActive(ConfirmedTokenActivetedEmail confirmedTokenActivetedEmail);
    void updateTokenInUserForResetPassword(User user, String token);
    void sendEmailWithResetToken(String userEmail, String resetToken, String url);
    void updateData(Long userId, UserUpdateDTO updateUser);
    User findById(Long id);
}
