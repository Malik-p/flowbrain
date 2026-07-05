package com.flowbrain.backend.ai.dto;

public class SprintSummaryResponse {

    private String summary;

    public SprintSummaryResponse() {
    }

    public SprintSummaryResponse(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}