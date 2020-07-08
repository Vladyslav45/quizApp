package com.example.quiz.repository;

import com.example.quiz.model.ConfirmedTokenActivetedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedTokenRepository extends JpaRepository<ConfirmedTokenActivetedEmail, Long> {
    ConfirmedTokenActivetedEmail findByConfirmToken(String confirmedToken);
}
