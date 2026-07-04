package com.flowbrain.backend.project.service;

import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.dto.ProjectResponse;

import java.util.*;

public interface ProjectService {

    ProjectResponse createProject(
            String workspaceId,
            CreateProjectRequest request);

    List<ProjectResponse> getAllProjects(
            String workspaceId);

    ProjectResponse getProjectById(String id);

    ProjectResponse updateProject(
            String id,
            CreateProjectRequest request);

    void deleteProject(String id);
}
