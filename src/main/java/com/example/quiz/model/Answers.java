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
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String correctAnswer;

    @Column
    private String answers1;

    @Column
    private String answer2;

    @Column
    private String answer3;

    @ManyToOne
    @JoinColumn(name = "themeSubject_id", nullable = false)
    private ThemeSubject themeSubject;
}
