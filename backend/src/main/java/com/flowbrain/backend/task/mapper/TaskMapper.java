package com.flowbrain.backend.task.mapper;

import com.flowbrain.backend.task.dto.TaskResponse;
import com.flowbrain.backend.task.entity.Task;

public class TaskMapper {

    public static TaskResponse toResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());

        response.setPriority(task.getPriority().name());
        response.setStatus(task.getStatus().name());

        response.setAssignedTo(task.getAssignedTo().getName());
        response.setCreatedBy(task.getCreatedBy().getName());

        response.setProjectName(task.getProject().getName());
        response.setWorkspaceName(task.getWorkspace().getName());

        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());

        return response;

    }

}