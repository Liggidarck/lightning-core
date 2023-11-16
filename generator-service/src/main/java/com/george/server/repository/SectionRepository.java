package com.george.server.repository;

import com.george.server.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SectionRepository extends JpaRepository<Section, Long> {
}