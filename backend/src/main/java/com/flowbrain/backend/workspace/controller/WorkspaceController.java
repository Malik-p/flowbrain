package com.flowbrain.backend.workspace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowbrain.backend.common.dto.ApiResponse;
import com.flowbrain.backend.workspace.dto.CreateWorkspaceRequest;
import com.flowbrain.backend.workspace.dto.InviteMemberRequest;
import com.flowbrain.backend.workspace.dto.WorkspaceResponse;
import com.flowbrain.backend.workspace.service.WorkspaceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/workspaces")
public class WorkspaceController {

        private final WorkspaceService workspaceService;

        public WorkspaceController(WorkspaceService workspaceService) {
                this.workspaceService = workspaceService;
        }

        @PostMapping
        public ResponseEntity<ApiResponse<WorkspaceResponse>> createWorkspace(
                        @Valid @RequestBody CreateWorkspaceRequest request) {

                WorkspaceResponse response = workspaceService.createWorkspace(request);

                ApiResponse<WorkspaceResponse> apiResponse = new ApiResponse<>(
                                true,
                                "Workspace created successfully",
                                response);

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(apiResponse);
        }

        @PostMapping("/{workspaceId}/invite")
        public ResponseEntity<ApiResponse<WorkspaceResponse>> inviteMember(

                        @PathVariable String workspaceId,

                        @Valid @RequestBody InviteMemberRequest request) {

                WorkspaceResponse response = workspaceService.inviteMember(
                                workspaceId,
                                request);

                ApiResponse<WorkspaceResponse> apiResponse = new ApiResponse<>(
                                true,
                                "Member invited successfully",
                                response);

                return ResponseEntity.ok(apiResponse);

        }

        @GetMapping
        public ResponseEntity<ApiResponse<List<WorkspaceResponse>>> getAllWorkspaces() {

                List<WorkspaceResponse> response = workspaceService.getAllWorkspaces();

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Workspaces fetched successfully",
                                                response));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<WorkspaceResponse>> getWorkspaceById(
                        @PathVariable String id) {

                WorkspaceResponse response = workspaceService.getWorkspaceById(id);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Workspace fetched successfully",
                                                response));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<WorkspaceResponse>> updateWorkspace(
                        @PathVariable String id,
                        @Valid @RequestBody CreateWorkspaceRequest request) {

                WorkspaceResponse response = workspaceService.updateWorkspace(id, request);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Workspace updated successfully",
                                                response));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<String>> deleteWorkspace(
                        @PathVariable String id) {

                workspaceService.deleteWorkspace(id);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Workspace deleted successfully",
                                                "Success"));
        }

}
