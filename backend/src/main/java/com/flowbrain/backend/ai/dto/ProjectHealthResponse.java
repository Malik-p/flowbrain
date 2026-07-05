package com.flowbrain.backend.ai.dto;

public class ProjectHealthResponse {

    private String report;

    public ProjectHealthResponse() {
    }

    public ProjectHealthResponse(String report) {
        this.report = report;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}