package com.example.orangeNote.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    @RequestMapping
    public void test() { System.out.println("project test"); }

    @ResponseBody
    @RequestMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createProject(@RequestBody Map<String, Object> input) {

    }
}
