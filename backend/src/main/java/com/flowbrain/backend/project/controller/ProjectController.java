package com.flowbrain.backend.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flowbrain.backend.common.dto.ApiResponse;
import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.dto.ProjectResponse;
import com.flowbrain.backend.project.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

        private final ProjectService projectService;

        public ProjectController(ProjectService projectService) {
                this.projectService = projectService;
        }

        // ================= CREATE PROJECT =================

        @PostMapping("/workspaces/{workspaceId}/projects")
        public ResponseEntity<ApiResponse<ProjectResponse>> createProject(

                        @PathVariable String workspaceId,

                        @Valid @RequestBody CreateProjectRequest request) {

                ProjectResponse response = projectService.createProject(
                                workspaceId,
                                request);

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(new ApiResponse<>(
                                                true,
                                                "Project created successfully",
                                                response));
        }

        // ================= GET ALL PROJECTS =================

        @GetMapping("/workspaces/{workspaceId}/projects")
        public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllProjects(

                        @PathVariable String workspaceId) {

                List<ProjectResponse> response = projectService.getAllProjects(workspaceId);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Projects fetched successfully",
                                                response));
        }

        // ================= GET PROJECT =================

        @GetMapping("/projects/{projectId}")
        public ResponseEntity<ApiResponse<ProjectResponse>> getProjectById(

                        @PathVariable String projectId) {

                ProjectResponse response = projectService.getProjectById(projectId);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Project fetched successfully",
                                                response));
        }

        // ================= UPDATE PROJECT =================

        @PutMapping("/projects/{projectId}")
        public ResponseEntity<ApiResponse<ProjectResponse>> updateProject(

                        @PathVariable String projectId,

                        @Valid @RequestBody CreateProjectRequest request) {

                ProjectResponse response = projectService.updateProject(
                                projectId,
                                request);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Project updated successfully",
                                                response));
        }

        // ================= DELETE PROJECT =================

        @DeleteMapping("/projects/{projectId}")
        public ResponseEntity<ApiResponse<String>> deleteProject(

                        @PathVariable String projectId) {

                projectService.deleteProject(projectId);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Project deleted successfully",
                                                "Success"));
        }

}