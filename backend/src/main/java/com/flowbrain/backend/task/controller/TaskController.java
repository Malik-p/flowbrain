package com.flowbrain.backend.task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flowbrain.backend.common.dto.ApiResponse;
import com.flowbrain.backend.task.dto.CreateTaskRequest;
import com.flowbrain.backend.task.dto.TaskResponse;
import com.flowbrain.backend.task.dto.UpdateTaskRequest;
import com.flowbrain.backend.task.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ================= CREATE TASK =================

    @PostMapping("/workspaces/{workspaceId}/projects/{projectId}/tasks")
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(

            @PathVariable String workspaceId,

            @PathVariable String projectId,

            @Valid @RequestBody CreateTaskRequest request) {

        TaskResponse response = taskService.createTask(
                workspaceId,
                projectId,
                request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Task created successfully",
                        response));
    }

    // ================= GET PROJECT TASKS =================

    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getProjectTasks(

            @PathVariable String projectId) {

        List<TaskResponse> response = taskService.getProjectTasks(projectId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Tasks fetched successfully",
                        response));
    }

    // ================= GET TASK =================

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(

            @PathVariable String taskId) {

        TaskResponse response = taskService.getTaskById(taskId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task fetched successfully",
                        response));
    }

    // ================= UPDATE TASK =================

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(

            @PathVariable String taskId,

            @Valid @RequestBody UpdateTaskRequest request) {

        TaskResponse response = taskService.updateTask(
                taskId,
                request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task updated successfully",
                        response));
    }

    // ================= DELETE TASK =================

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse<String>> deleteTask(

            @PathVariable String taskId) {

        taskService.deleteTask(taskId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task deleted successfully",
                        "Success"));
    }
}