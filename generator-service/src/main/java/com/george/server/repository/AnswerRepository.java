package com.george.server.repository;

import com.george.server.model.questions.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}