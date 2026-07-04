package com.flowbrain.backend.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateWorkspaceRequest {

    @NotBlank(message = "workspace name is required")
    @Size(min = 3, max = 50, message = "Workspae name must be between 3 and 50 characters")
    private String name;

    @Size(max = 300, message = "Description cannot exceed 300 characters")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
