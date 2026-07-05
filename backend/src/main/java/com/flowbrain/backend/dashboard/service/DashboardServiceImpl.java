package com.flowbrain.backend.dashboard.service;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.common.CurrentUserService;
import com.flowbrain.backend.dashboard.dto.DashboardResponse;
import com.flowbrain.backend.project.repository.ProjectRepository;
import com.flowbrain.backend.task.enums.Status;
import com.flowbrain.backend.task.repository.TaskRepository;
import com.flowbrain.backend.user.entity.User;
import com.flowbrain.backend.workspace.repository.WorkspaceRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final WorkspaceRepository workspaceRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final CurrentUserService currentUserService;

    public DashboardServiceImpl(
            WorkspaceRepository workspaceRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            CurrentUserService currentUserService) {

        this.workspaceRepository = workspaceRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.currentUserService = currentUserService;
    }

    @Override
    public DashboardResponse getDashboard() {

        User currentUser = currentUserService.getCurrentUser();

        DashboardResponse response = new DashboardResponse();

        response.setTotalWorkspaces(
                workspaceRepository.countByMembers_Id(
                        currentUser.getId()));

        response.setTotalProjects(
                projectRepository.countByCreatedBy_Id(
                        currentUser.getId()));

        response.setTotalTasks(
                taskRepository.countByCreatedBy_Id(
                        currentUser.getId()));

        response.setCompletedTasks(
                taskRepository.countByCreatedBy_IdAndStatus(
                        currentUser.getId(),
                        Status.DONE));

        response.setInProgressTasks(
                taskRepository.countByCreatedBy_IdAndStatus(
                        currentUser.getId(),
                        Status.IN_PROGRESS));

        response.setTodoTasks(
                taskRepository.countByCreatedBy_IdAndStatus(
                        currentUser.getId(),
                        Status.TODO));

        return response;
    }

}