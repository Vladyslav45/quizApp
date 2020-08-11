package com.example.quiz.repository;

import com.example.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Transactional
    @Query("select distinct q from Question q inner join q.themeSubject t where t.name = :themeSubjectName")
    List<Question> findAllQuestion(@Param("themeSubjectName") String nameThemeSubject);
}
