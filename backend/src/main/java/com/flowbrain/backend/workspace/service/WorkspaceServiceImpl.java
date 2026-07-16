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
import com.flowbrain.backend.workspace.dto.MemberResponse;
import com.flowbrain.backend.workspace.dto.WorkspaceResponse;
import com.flowbrain.backend.workspace.entity.Workspace;
import com.flowbrain.backend.workspace.mapper.WorkspaceMapper;
import com.flowbrain.backend.workspace.repository.WorkspaceRepository;
import com.flowbrain.backend.workspace.dto.MemberResponse;

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

                // User can belong to only one workspace
                if (workspaceRepository.countByMembers_Id(currentUser.getId()) > 0) {
                        throw new BadRequestException(
                                        "You already belong to a workspace");
                }

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

                // Only members of this workspace can access it
                authorizationService.validateWorkspaceMember(workspace);

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

                User currentUser = currentUserService.getCurrentUser();

                Workspace workspace = workspaceRepository
                                .findById(workspaceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

                // Only owner can invite members
                authorizationService.validateWorkspaceOwner(workspace);

                // User must already be registered
                User member = userRepository
                                .findByEmail(request.getEmail())
                                .orElseThrow(() -> new BadRequestException(
                                                "User must register before being invited"));

                // Owner cannot invite himself
                if (member.getId().equals(currentUser.getId())) {

                        throw new BadRequestException(
                                        "You cannot invite yourself");

                }

                // User can belong to only one workspace
                long workspaceCount = workspaceRepository.countByMembers_Id(
                                member.getId());

                if (workspaceCount > 0) {

                        throw new BadRequestException(
                                        "User already belongs to another workspace");

                }

                // Extra safety check
                boolean alreadyMember = workspace.getMembers()
                                .stream()
                                .anyMatch(user -> user.getId().equals(member.getId()));

                if (alreadyMember) {

                        throw new BadRequestException(
                                        "User is already a member");

                }

                // Add member
                workspace.getMembers().add(member);

                Workspace savedWorkspace = workspaceRepository.save(workspace);

                // Create notification
                notificationService.createNotification(
                                member,
                                "Workspace Invitation",
                                "You have been added to workspace " + workspace.getName(),
                                NotificationType.WORKSPACE_INVITE);

                return WorkspaceMapper.toResponse(savedWorkspace);
        }

        @Override
        public List<MemberResponse> getWorkspaceMembers(String workspaceId) {

                Workspace workspace = workspaceRepository
                                .findById(workspaceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

                authorizationService.validateWorkspaceMember(workspace);

                return workspace.getMembers()
                                .stream()
                                .map(member -> {

                                        MemberResponse response = new MemberResponse();

                                        response.setId(member.getId());
                                        response.setName(member.getName());
                                        response.setEmail(member.getEmail());
                                        response.setRole(member.getRole().name());

                                        return response;

                                })
                                .toList();
        }

}
