package com.george.server.controller;

import com.george.server.model.Theme;
import com.george.server.repository.SectionRepository;
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
public class ThemeController {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    ThemeRepository themeRepository;

    @PostMapping("/theme.create")
    private ResponseEntity<?> createTheme(@RequestParam(value = "section_id") Long sectionId,
                                          @RequestBody Theme themeRequest) {
        Theme theme = sectionRepository.findById(sectionId).map(section -> {
            themeRequest.setSection(section);
            return themeRepository.save(themeRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Section with id " + sectionId + " not found"));

        return new ResponseEntity<>(theme, HttpStatus.CREATED);
    }

}
