package com.example.quiz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "answers")
public class QuizAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String correctAnswer;

    @Column
    private String answers1;

    @Column
    private String answer2;

    @Column
    private String answer3;

    @ManyToOne
    @JoinColumn(name = "quizCategory_id", nullable = false)
    private QuizCategory quizCategory;
}
