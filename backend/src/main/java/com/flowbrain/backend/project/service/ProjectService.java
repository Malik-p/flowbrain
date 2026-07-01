package com.flowbrain.backend.project.service;

import com.flowbrain.backend.project.dto.CreateProjectRequest;

public interface ProjectService {
    
    CreateProjectRequest createProject(CreateProjectRequest request);
}
