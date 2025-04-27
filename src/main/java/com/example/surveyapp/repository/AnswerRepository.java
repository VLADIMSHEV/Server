package com.example.surveyapp.repository;

import com.example.surveyapp.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
