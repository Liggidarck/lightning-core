package com.george.server.controller;

import com.george.server.model.questions.Answer;
import com.george.server.repository.AnswerRepository;
import com.george.server.repository.QuestionRepository;
import com.george.server.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;


    @PostMapping("/answer.addToQuestion")
    private ResponseEntity<?> addAnswer(@RequestParam(value = "question_id") Long questionId,
                                        @RequestBody Answer answerRequest) {


        Answer answer = questionRepository.findById(questionId).map(question -> {

            Long answerId = answerRequest.getId();
            if(answerId != null) {
                Answer _answer = answerRepository.findById(answerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found!"));

                question.addAnswer(_answer);
                questionRepository.save(question);
                return _answer;
            }

            question.addAnswer(answerRequest);
            return answerRepository.save(answerRequest);

        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));


        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

}
