package com.george.server.repository;

import com.george.server.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}