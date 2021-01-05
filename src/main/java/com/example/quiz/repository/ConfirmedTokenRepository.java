package com.example.quiz.repository;

import com.example.quiz.model.ConfirmedTokenActivetedEmail;
import com.example.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ConfirmedTokenRepository extends JpaRepository<ConfirmedTokenActivetedEmail, Long> {
    ConfirmedTokenActivetedEmail findByConfirmToken(String confirmedToken);

    @Transactional
    @Modifying
    @Query(value = "delete from ConfirmedTokenActivetedEmail ctae where ctae.user = :user")
    void deleteByUser(@Param("user") User user);
}
