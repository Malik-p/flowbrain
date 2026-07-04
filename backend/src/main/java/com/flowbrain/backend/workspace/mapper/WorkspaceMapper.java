package com.flowbrain.backend.workspace.mapper;

import com.flowbrain.backend.workspace.dto.WorkspaceResponse;
import com.flowbrain.backend.workspace.entity.Workspace;

public class WorkspaceMapper {

    public static WorkspaceResponse toResponse(Workspace workspace) {

        WorkspaceResponse response = new WorkspaceResponse();

        response.setId(workspace.getId());
        response.setName(workspace.getName());
        response.setDescription(workspace.getDescription());
        response.setOwnerName(workspace.getOwner().getName());
        response.setTotalMembers(workspace.getMembers().size());
        response.setCreatedAt(workspace.getCreatedAt());

        return response;
    }
}