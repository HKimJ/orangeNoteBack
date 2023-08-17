package com.example.orangeNote.project.controller;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @ResponseBody
    @RequestMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody Map<String, Object> input) {
        Map<String, Object> temp = new HashMap<>(input);
        Map<String, Object> response = new HashMap<>();

//        response = projectService.

        return ResponseEntity.ok(null);
    }
}
