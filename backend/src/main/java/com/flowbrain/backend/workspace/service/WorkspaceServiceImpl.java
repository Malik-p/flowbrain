package com.flowbrain.backend.workspace.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.common.CurrentUserService;
import com.flowbrain.backend.user.entity.User;
import com.flowbrain.backend.user.repository.UserRepository;
import com.flowbrain.backend.workspace.dto.CreateWorkspaceRequest;
import com.flowbrain.backend.workspace.dto.InviteMemberRequest;
import com.flowbrain.backend.workspace.dto.WorkspaceResponse;
import com.flowbrain.backend.workspace.entity.Workspace;
import com.flowbrain.backend.workspace.mapper.WorkspaceMapper;
import com.flowbrain.backend.workspace.repository.WorkspaceRepository;

import com.flowbrain.backend.common.AuthorizationService;
import com.flowbrain.backend.common.exception.BadRequestException;
import com.flowbrain.backend.common.exception.ResourceNotFoundException;
import com.flowbrain.backend.notification.enums.NotificationType;
import com.flowbrain.backend.notification.service.NotificationService;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

        private final WorkspaceRepository workspaceRepository;
        private final CurrentUserService currentUserService;
        private final UserRepository userRepository;
        private final AuthorizationService authorizationService;
        private final NotificationService notificationService;

        public WorkspaceServiceImpl(
                        WorkspaceRepository workspaceRepository,
                        CurrentUserService currentUserService, UserRepository userRepository,
                        AuthorizationService authorizationService, NotificationService notificationService) {

                this.workspaceRepository = workspaceRepository;
                this.currentUserService = currentUserService;
                this.userRepository = userRepository;
                this.authorizationService = authorizationService;
                this.notificationService = notificationService;
        }

        @Override
        public WorkspaceResponse createWorkspace(CreateWorkspaceRequest request) {

                User currentUser = currentUserService.getCurrentUser();

                Workspace workspace = new Workspace();

                workspace.setName(request.getName());
                workspace.setDescription(request.getDescription());
                workspace.setOwner(currentUser);
                workspace.setCreatedAt(LocalDateTime.now());

                List<User> members = new ArrayList<>();
                members.add(currentUser);

                workspace.setMembers(members);

                Workspace savedWorkspace = workspaceRepository.save(workspace);

                return WorkspaceMapper.toResponse(savedWorkspace);
        }

        @Override
        public List<WorkspaceResponse> getAllWorkspaces() {

                User currentUser = currentUserService.getCurrentUser();

                List<Workspace> workspaces = workspaceRepository.findByMembers_Id(currentUser.getId());

                return workspaces.stream()
                                .map(WorkspaceMapper::toResponse)
                                .toList();
        }

        @Override
        public WorkspaceResponse getWorkspaceById(String id) {

                Workspace workspace = workspaceRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

                return WorkspaceMapper.toResponse(workspace);
        }

        @Override
        public WorkspaceResponse updateWorkspace(
                        String id,
                        CreateWorkspaceRequest request) {

                Workspace workspace = workspaceRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Workspace not found"));

                authorizationService.validateWorkspaceOwner(workspace);

                workspace.setName(request.getName());
                workspace.setDescription(request.getDescription());

                Workspace savedWorkspace = workspaceRepository.save(workspace);

                return WorkspaceMapper.toResponse(savedWorkspace);
        }

        @Override
        public void deleteWorkspace(String id) {

                Workspace workspace = workspaceRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Workspace not found"));

                authorizationService.validateWorkspaceOwner(workspace);

                workspaceRepository.delete(workspace);
        }

        @Override
        public WorkspaceResponse inviteMember(
                        String workspaceId,
                        InviteMemberRequest request) {

                Workspace workspace = workspaceRepository.findById(workspaceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

                authorizationService.validateWorkspaceOwner(workspace);

                System.out.println("Request Email = " + request.getEmail());

                System.out.println("Repository Result = " +
                                userRepository.findByEmail(request.getEmail()));

                User member = userRepository
                                .findByEmail(request.getEmail())
                                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

                boolean alreadyMember = workspace
                                .getMembers()
                                .stream()
                                .anyMatch(user -> user.getId().equals(member.getId()));

                if (alreadyMember) {
                        throw new BadRequestException(
                                        "User is already a member");
                }

                workspace.getMembers().add(member);

                Workspace savedWorkspace = workspaceRepository.save(workspace);

                notificationService.createNotification(
                                member,
                                "Workspace Invitation",
                                "You have been added to workspace "
                                                + workspace.getName(),
                                NotificationType.WORKSPACE_INVITE);

                return WorkspaceMapper.toResponse(savedWorkspace);
        }

}
