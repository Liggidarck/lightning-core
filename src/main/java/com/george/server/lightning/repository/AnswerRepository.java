package com.george.server.lightning.repository;

import com.george.server.lightning.model.questions.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}