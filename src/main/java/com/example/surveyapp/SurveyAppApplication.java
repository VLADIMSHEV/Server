package com.example.surveyapp;

import com.example.surveyapp.Question;
import com.example.surveyapp.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SurveyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurveyAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository) {
        return args -> {
            if (questionRepository.count() == 0) {
                Question q1 = new Question();
                q1.setText("Как вам наш курс?");
                q1.setOptions(Arrays.asList("Отлично", "Хорошо", "Нормально", "Плохо"));
                questionRepository.save(q1);

                Question q2 = new Question();
                q2.setText("Что бы вы хотели улучшить?");
                q2.setOptions(Arrays.asList("Содержание", "Преподавание", "Практические задания", "Другое"));
                questionRepository.save(q2);
            }
        };
    }
}
