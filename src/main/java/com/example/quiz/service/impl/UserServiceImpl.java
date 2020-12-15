package com.example.quiz.service.impl;

import com.example.quiz.config.EmailSender;
import com.example.quiz.model.ConfirmedTokenActivetedEmail;
import com.example.quiz.model.Role;
import com.example.quiz.model.User;
import com.example.quiz.repository.ConfirmedTokenRepository;
import com.example.quiz.repository.RoleRepository;
import com.example.quiz.repository.UserRepository;
import com.example.quiz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    private EmailSender emailSender;
    private ConfirmedTokenRepository confirmedTokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder, EmailSender emailSender,ConfirmedTokenRepository confirmedTokenRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailSender = emailSender;
        this.confirmedTokenRepository = confirmedTokenRepository;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRoleType("USER");
        user.setRole(role);
        user.setActive(0);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByResetToken(String token) {
        return userRepository.findByTokenForResetPassword(token);
    }

    @Override
    public void changePassword(String pass, User user) {
        userRepository.findById(user.getId()).ifPresent(u -> {
            u.setPassword(bCryptPasswordEncoder.encode(pass));
            u.setTokenForResetPassword(null);
            userRepository.save(u);
        });
    }

    @Override
    public void ConfirmedAccount(User user, String url) {
        ConfirmedTokenActivetedEmail confirmedToken = new ConfirmedTokenActivetedEmail(user);
        confirmedTokenRepository.save(confirmedToken);
        emailSender.sendConfirmedToken(user.getEmail(), confirmedToken.getConfirmToken(), url);
    }

    @Override
    public ConfirmedTokenActivetedEmail findByConfirmToken(String confirmToken) {
        return confirmedTokenRepository.findByConfirmToken(confirmToken);
    }

    @Override
    public void updateActive(ConfirmedTokenActivetedEmail confirmedTokenActivetedEmail) {
        User user = userRepository.findByEmail(confirmedTokenActivetedEmail.getUser().getEmail());
        userRepository.updateActive(user.getId());
    }

    @Override
    public void updateTokenInUserForResetPassword(User user, String token) {
        userRepository.findById(user.getId()).ifPresent(u -> userRepository.updateResetTokenInUser(token, u.getId()));
    }

    @Override
    public void sendEmailWithResetToken(String userEmail, String resetToken, String url) {
        emailSender.sendResetPassword(userEmail, resetToken,url);
    }
}
