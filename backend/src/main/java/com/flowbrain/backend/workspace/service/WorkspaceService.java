package com.flowbrain.backend.workspace.service;

import java.util.List;

import com.flowbrain.backend.workspace.dto.CreateWorkspaceRequest;
import com.flowbrain.backend.workspace.dto.InviteMemberRequest;
import com.flowbrain.backend.workspace.dto.MemberResponse;
import com.flowbrain.backend.workspace.dto.WorkspaceResponse;
import com.flowbrain.backend.workspace.dto.MemberResponse;

public interface WorkspaceService {

    WorkspaceResponse createWorkspace(CreateWorkspaceRequest request);

    List<WorkspaceResponse> getAllWorkspaces();

    WorkspaceResponse getWorkspaceById(String id);

    WorkspaceResponse updateWorkspace(String id, CreateWorkspaceRequest request);

    void deleteWorkspace(String id);

    WorkspaceResponse inviteMember(
            String workspaceId,
            InviteMemberRequest request);

            List<MemberResponse> getWorkspaceMembers(String workspaceId);
}