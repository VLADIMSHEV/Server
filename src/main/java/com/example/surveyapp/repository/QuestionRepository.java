package com.example.surveyapp.repository;

import com.example.surveyapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
