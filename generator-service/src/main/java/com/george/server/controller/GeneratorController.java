package com.george.server.controller;

import com.george.server.model.core.LowLevelPrimitive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {

    private ResponseEntity<?> generateHighPrimitive(@RequestBody LowLevelPrimitive lowLevelPrimitive) {


        return null;
    }

}
