package com.flowbrain.backend.task.service;

import java.util.List;

import com.flowbrain.backend.task.dto.CreateTaskRequest;
import com.flowbrain.backend.task.dto.TaskResponse;
import com.flowbrain.backend.task.dto.UpdateTaskRequest;

public interface TaskService {

    TaskResponse createTask(
            String workspaceId,
            String projectId,
            CreateTaskRequest request);

    List<TaskResponse> getProjectTasks(
            String projectId);

    TaskResponse getTaskById(
            String taskId);

    TaskResponse updateTask(
            String taskId,
            UpdateTaskRequest request);

    void deleteTask(
            String taskId);

}