package com.flowbrain.backend.ai.dto;

public class TaskBreakdownResponse {

    private String breakdown;

    public TaskBreakdownResponse() {
    }

    public TaskBreakdownResponse(String breakdown) {
        this.breakdown = breakdown;
    }

    public String getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(String breakdown) {
        this.breakdown = breakdown;
    }

}
