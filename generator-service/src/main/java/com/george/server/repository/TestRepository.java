package com.george.server.repository;

import com.george.server.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestRepository extends JpaRepository<Test, Long> {
}