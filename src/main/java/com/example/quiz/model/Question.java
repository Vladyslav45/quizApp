package com.example.quiz.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameQuestion;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Answers answers;

    @ManyToOne
    @JoinColumn(name = "themeSubject_id", nullable = false)
    private ThemeSubject themeSubject;

    @Override
    public String toString() {
        return "Question{" +
                "nameQuestion='" + nameQuestion + '\'' +
                ", answers=" + answers +
                '}';
    }
}
