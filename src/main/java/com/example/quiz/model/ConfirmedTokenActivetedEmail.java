package com.example.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmToken")
public class ConfirmedTokenActivetedEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String confirmToken;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ConfirmedTokenActivetedEmail(User user) {
        this.user = user;
        this.createDate = new Date();
        this.confirmToken = UUID.randomUUID().toString();
    }
}
