package com.george.server.controller;

import com.george.server.model.Theme;
import com.george.server.model.questions.Question;
import com.george.server.repository.QuestionRepository;
import com.george.server.repository.ThemeRepository;
import com.george.server.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ThemeRepository themeRepository;

    @PostMapping("/question.create")
    private ResponseEntity<?> createQuestion(@RequestParam(value = "theme_id") Long themeId,
                                             @RequestBody Question questionRequest) {
        Question question = themeRepository.findById(themeId).map(theme -> {
            questionRequest.setTheme(theme);
            return questionRepository.save(questionRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

}
