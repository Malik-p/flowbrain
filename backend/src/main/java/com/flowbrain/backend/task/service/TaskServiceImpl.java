package com.flowbrain.backend.task.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.common.CurrentUserService;
import com.flowbrain.backend.project.entity.Project;
import com.flowbrain.backend.project.repository.ProjectRepository;
import com.flowbrain.backend.task.dto.CreateTaskRequest;
import com.flowbrain.backend.task.dto.TaskResponse;
import com.flowbrain.backend.task.dto.UpdateTaskRequest;
import com.flowbrain.backend.task.entity.Task;
import com.flowbrain.backend.task.enums.Status;
import com.flowbrain.backend.task.mapper.TaskMapper;
import com.flowbrain.backend.task.repository.TaskRepository;
import com.flowbrain.backend.user.entity.User;
import com.flowbrain.backend.user.repository.UserRepository;
import com.flowbrain.backend.workspace.entity.Workspace;
import com.flowbrain.backend.workspace.repository.WorkspaceRepository;
import com.flowbrain.backend.common.AuthorizationService;
import com.flowbrain.backend.common.exception.ResourceNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final AuthorizationService authorizationService;

    public TaskServiceImpl(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            WorkspaceRepository workspaceRepository,
            UserRepository userRepository,
            CurrentUserService currentUserService, AuthorizationService authorizationService) {

        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.authorizationService = authorizationService;
    }

    @Override
    public TaskResponse createTask(
            String workspaceId,
            String projectId,
            CreateTaskRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Workspace workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        authorizationService.validateWorkspaceMember(workspace);

        User assignedUser = userRepository
                .findById(request.getAssignedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        task.setPriority(request.getPriority());

        task.setStatus(Status.TODO);

        task.setDueDate(request.getDueDate());

        task.setAssignedTo(assignedUser);

        task.setCreatedBy(currentUser);

        task.setWorkspace(workspace);

        task.setProject(project);

        task.setCreatedAt(LocalDateTime.now());

        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        return TaskMapper.toResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getProjectTasks(
            String projectId) {

        return taskRepository
                .findByProjectId(projectId)
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse getTaskById(String taskId) {

        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return TaskMapper.toResponse(task);
    }

    @Override
    public TaskResponse updateTask(
            String taskId,
            UpdateTaskRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        authorizationService.validateWorkspaceOwner(
                task.getWorkspace());

        if (request.getTitle() != null)
            task.setTitle(request.getTitle());

        if (request.getDescription() != null)
            task.setDescription(request.getDescription());

        if (request.getPriority() != null)
            task.setPriority(request.getPriority());

        if (request.getStatus() != null)
            task.setStatus(request.getStatus());

        if (request.getDueDate() != null)
            task.setDueDate(request.getDueDate());

        if (request.getAssignedUserId() != null) {

            User assignedUser = userRepository
                    .findById(request.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));

            task.setAssignedTo(assignedUser);
        }

        task.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.toResponse(updatedTask);
    }

    @Override
    public void deleteTask(String taskId) {

        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        authorizationService.validateWorkspaceOwner(
                task.getWorkspace());

        taskRepository.delete(task);
    }
}