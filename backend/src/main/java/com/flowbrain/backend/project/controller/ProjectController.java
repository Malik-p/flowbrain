package com.flowbrain.backend.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.*;

import com.flowbrain.backend.common.dto.ApiResponse;
import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.dto.ProjectResponse;
import com.flowbrain.backend.project.service.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(
            @Valid @RequestBody CreateProjectRequest request) {

        ProjectResponse response = projectService.createProject(request);

        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(
                true,
                "Project created successfully",
                response);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllProjects() {
        List<ProjectResponse> response = projectService.getAllProjects();

        ApiResponse<List<ProjectResponse>> apiResponse = new ApiResponse<>(
                true,
                "List of all the projects",
                response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProjectById(@PathVariable String id) {
        ProjectResponse response = projectService.getProjectById(id);

        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(
                true,
                "Project found successfully",
                response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(
            @PathVariable String id) {

        projectService.deleteProject(id);

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Project deleted successfully",
                null);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> updateProject(@PathVariable String id,
            @Valid @RequestBody CreateProjectRequest request) {
        ProjectResponse response = projectService.updateProject(id, request);

        ApiResponse<ProjectResponse> apiResponse = new ApiResponse<>(
                true,
                "Project updated successfully",
                response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }

}
