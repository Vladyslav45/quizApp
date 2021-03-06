package com.example.quiz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(max = 50)
    private String name;

    @Column
    @Email
    private String email;

    @Column
    @Size(min = 6)
    private String password;

    @Column
    private int active;

    @Column
    private String tokenForResetPassword;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<HistoryQuiz> historyQuiz;

    @OneToOne
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;
}
