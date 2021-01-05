package com.example.quiz.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "history_quiz")
public class HistoryQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String theme;

    @Column
    private String result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
