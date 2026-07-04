package com.flowbrain.backend.project.mapper;

import com.flowbrain.backend.project.dto.ProjectResponse;
import com.flowbrain.backend.project.entity.Project;

public class ProjectMapper {

    public static ProjectResponse toResponse(Project project) {

        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setColor(project.getColor());

        response.setWorkspaceName(
                project.getWorkspace().getName());

        response.setCreatedBy(
                project.getCreatedBy().getName());

        response.setCreatedAt(
                project.getCreatedAt());

        return response;

    }

}