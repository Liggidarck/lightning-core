package com.george.server.controller;

import com.george.server.model.Section;
import com.george.server.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @PostMapping("/section.create")
    private ResponseEntity<?> createSection(@RequestBody Section section) {
        Section _section = sectionRepository.save(section);
        return new ResponseEntity<>(_section, HttpStatus.CREATED);
    }


}
