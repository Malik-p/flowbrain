package com.flowbrain.backend.ai.context;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flowbrain.backend.project.entity.Project;
import com.flowbrain.backend.project.repository.ProjectRepository;
import com.flowbrain.backend.task.entity.Task;
import com.flowbrain.backend.task.repository.TaskRepository;
import com.flowbrain.backend.workspace.entity.Workspace;
import com.flowbrain.backend.workspace.repository.WorkspaceRepository;

@Component
public class AIContextBuilder {

    private final WorkspaceRepository workspaceRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public AIContextBuilder(
            WorkspaceRepository workspaceRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository) {

        this.workspaceRepository = workspaceRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public String buildWorkspaceContext(
            String workspaceId) {

        Workspace workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace not found"));

        List<Project> projects = projectRepository.findByWorkspaceId(workspaceId);

        List<Task> tasks = taskRepository.findByWorkspaceId(workspaceId);

        StringBuilder context = new StringBuilder();

        context.append("Workspace : ")
                .append(workspace.getName())
                .append("\n\n");

        context.append("Projects :\n");

        for (Project project : projects) {

            context.append("- ")
                    .append(project.getName())
                    .append("\n");
        }

        context.append("\nTasks :\n");

        for (Task task : tasks) {

            context.append("- ")
                    .append(task.getTitle())
                    .append(" (")
                    .append(task.getStatus())
                    .append(")\n");
        }

        return context.toString();
    }

}