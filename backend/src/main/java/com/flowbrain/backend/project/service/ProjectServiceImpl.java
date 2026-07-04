package com.flowbrain.backend.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.common.CurrentUserService;
import com.flowbrain.backend.project.dto.CreateProjectRequest;
import com.flowbrain.backend.project.dto.ProjectResponse;
import com.flowbrain.backend.project.entity.Project;
import com.flowbrain.backend.project.mapper.ProjectMapper;
import com.flowbrain.backend.project.repository.ProjectRepository;
import com.flowbrain.backend.user.entity.User;
import com.flowbrain.backend.workspace.entity.Workspace;
import com.flowbrain.backend.workspace.repository.WorkspaceRepository;
import com.flowbrain.backend.common.AuthorizationService;
import com.flowbrain.backend.common.exception.ResourceNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

        private final ProjectRepository projectRepository;
        private final WorkspaceRepository workspaceRepository;
        private final CurrentUserService currentUserService;
        private final AuthorizationService authorizationService;

        public ProjectServiceImpl(
                        ProjectRepository projectRepository,
                        WorkspaceRepository workspaceRepository,
                        CurrentUserService currentUserService, AuthorizationService authorizationService) {

                this.projectRepository = projectRepository;
                this.workspaceRepository = workspaceRepository;
                this.currentUserService = currentUserService;
                this.authorizationService = authorizationService;
        }

        @Override
        public ProjectResponse createProject(
                        String workspaceId,
                        CreateProjectRequest request) {

                User currentUser = currentUserService.getCurrentUser();

                Workspace workspace = workspaceRepository
                                .findById(workspaceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

                authorizationService.validateWorkspaceMember(workspace);

                Project project = new Project();

                project.setName(request.getName());
                project.setDescription(request.getDescription());
                project.setColor(request.getColor());

                project.setWorkspace(workspace);
                project.setCreatedBy(currentUser);
                project.setCreatedAt(LocalDateTime.now());

                Project savedProject = projectRepository.save(project);

                return ProjectMapper.toResponse(savedProject);
        }

        @Override
        public List<ProjectResponse> getAllProjects(
                        String workspaceId) {

                User currentUser = currentUserService.getCurrentUser();

                Workspace workspace = workspaceRepository
                                .findById(workspaceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

                authorizationService.validateWorkspaceMember(workspace);

                return projectRepository
                                .findByWorkspaceId(workspaceId)
                                .stream()
                                .map(ProjectMapper::toResponse)
                                .toList();
        }

        @Override
        public ProjectResponse getProjectById(String id) {

                Project project = projectRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

                return ProjectMapper.toResponse(project);
        }

        @Override
        public ProjectResponse updateProject(
                        String id,
                        CreateProjectRequest request) {

                Project project = projectRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

                project.setName(request.getName());
                project.setDescription(request.getDescription());
                project.setColor(request.getColor());

                authorizationService.validateWorkspaceOwner(
                                project.getWorkspace());

                Project savedProject = projectRepository.save(project);

                return ProjectMapper.toResponse(savedProject);
        }

        @Override
        public void deleteProject(String id) {

                Project project = projectRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

                authorizationService.validateWorkspaceOwner(
                                project.getWorkspace());
                projectRepository.delete(project);
        }
}