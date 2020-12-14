package com.example.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "answer")
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String correctAnswer;

    @Column
    private String inCorrectAnswer1;

    @Column
    private String inCorrectAnswer2;

    @Column
    private String inCorrectAnswer3;

    @Column
    private String describeAnswerIfChoiceWrong;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "answer")
    @JsonIgnore
    private Question question;



}
