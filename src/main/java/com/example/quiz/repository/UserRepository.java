package com.example.quiz.repository;

import com.example.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByTokenForResetPassword(String token);

    @Transactional
    @Modifying
    @Query(value = "update User set active = 1 where id = :userId")
    void updateActive(@Param("userId") Long id);

    @Transactional
    @Modifying
    @Query(value = "update User set tokenForResetPassword = :token where id = :userId")
    void updateResetTokenInUser(@Param("token") String token, @Param("userId") Long userId);
}
