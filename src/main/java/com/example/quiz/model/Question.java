package com.example.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String nameQuestion;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "answer_id", nullable = false, unique = true)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "themeSubject_id", nullable = false)
    @JsonIgnore
    private ThemeSubject themeSubject;
}
