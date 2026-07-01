package com.flowbrain.backend.project.service;

import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.entity.Project;
import com.flowbrain.backend.project.repository.ProjectRepository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public CreateProjectRequest createProject(CreateProjectRequest request) {

        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setColor(request.getColor());
        project.setCreatedAt(LocalDateTime.now());

        projectRepository.save(project);
        return request;
    }

}
