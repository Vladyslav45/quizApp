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
    private String correctAnswer;

    @Column
    private String answers1;

    @Column
    private String answer2;

    @Column
    private String answer3;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

//    @Override
//    public String toString() {
//        return "Answers{" +
//                "correctAnswer='" + correctAnswer + '\'' +
//                ", answers1='" + answers1 + '\'' +
//                ", answer2='" + answer2 + '\'' +
//                ", answer3='" + answer3 + '\'' +
//                '}';
//    }
}
