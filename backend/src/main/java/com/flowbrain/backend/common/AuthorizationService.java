package com.flowbrain.backend.common;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.common.exception.ForbiddenException;
import com.flowbrain.backend.project.entity.Project;
import com.flowbrain.backend.user.entity.User;
import com.flowbrain.backend.workspace.entity.Workspace;

@Service
public class AuthorizationService {

    private final CurrentUserService currentUserService;

    public AuthorizationService(
            CurrentUserService currentUserService) {

        this.currentUserService = currentUserService;
    }

    // ================= WORKSPACE OWNER =================

    public void validateWorkspaceOwner(
            Workspace workspace) {

        User currentUser = currentUserService.getCurrentUser();

        if (!workspace.getOwner()
                .getId()
                .equals(currentUser.getId())) {

            throw new ForbiddenException(
                    "Only workspace owner can perform this action");
        }
    }

    // ================= WORKSPACE MEMBER =================

    public void validateWorkspaceMember(
            Workspace workspace) {

        User currentUser = currentUserService.getCurrentUser();

        boolean isMember = workspace.getMembers()
                .stream()
                .anyMatch(user -> user.getId().equals(currentUser.getId()));

        if (!isMember) {

            throw new ForbiddenException(
                    "You are not a member of this workspace");
        }
    }

    // ================= PROJECT ACCESS =================

    public void validateProjectAccess(
            Project project) {

        validateWorkspaceMember(
                project.getWorkspace());
    }

}