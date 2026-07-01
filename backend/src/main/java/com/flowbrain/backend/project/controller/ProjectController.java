package com.flowbrain.backend.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.service.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public CreateProjectRequest createProject(@Valid @RequestBody CreateProjectRequest request) {
        return projectService.createProject(request);
    }

}
