package com.george.server.lightning.repository;

import com.george.server.lightning.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}