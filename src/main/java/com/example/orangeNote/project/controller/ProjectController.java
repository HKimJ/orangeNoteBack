package com.example.orangeNote.project.controller;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @RequestMapping("/sample")
    public void sample() {
        List<ProjectDomain> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProjectDomain project = new ProjectDomain();
            project.setProjectName("project" + i);
            list.add(project);
        }
        projectService.putData(list);
    }

    @ResponseBody
    @RequestMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createProject(@RequestBody Map<String, Object> input) {

    }
}
