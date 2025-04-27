package com.example.surveyapp;

import com.example.surveyapp.Answer;
import com.example.surveyapp.Question;
import com.example.surveyapp.repository.AnswerRepository;
import com.example.surveyapp.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SurveyController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public SurveyController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("/")
    public String showSurvey(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "survey";
    }

    @PostMapping("/submit")
    public String submitAnswers(@RequestParam MultiValueMap<String, String> formData, Model model) {
        formData.forEach((key, values) -> {
            if (key.startsWith("question_")) {
                Long questionId = Long.parseLong(key.substring(9));
                String answerText = values.get(0);
                Answer answer = new Answer();
                answer.setQuestionId(questionId);
                answer.setAnswerText(answerText);
                answerRepository.save(answer);
            }
        });
        model.addAttribute("message", "Спасибо за участие в опросе!");
        return "result";
    }
}
