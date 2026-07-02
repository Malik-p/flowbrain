package com.flowbrain.backend.project.service;

import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.dto.ProjectResponse;

import java.util.*;

public interface ProjectService {

    ProjectResponse createProject(CreateProjectRequest request);

    List<ProjectResponse> getAllProjects();

    ProjectResponse getProjectById(String id);

    void deleteProject(String id);

    ProjectResponse updateProject(String id, CreateProjectRequest request);
}
