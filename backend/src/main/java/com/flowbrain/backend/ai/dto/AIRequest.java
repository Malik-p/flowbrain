package com.flowbrain.backend.ai.dto;

import jakarta.validation.constraints.NotBlank;

public class AIRequest {

    @NotBlank
    private String prompt;

    private String workspaceId;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

}