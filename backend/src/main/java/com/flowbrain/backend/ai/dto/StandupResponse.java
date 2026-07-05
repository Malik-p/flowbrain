package com.flowbrain.backend.ai.dto;

public class StandupResponse {

    private String standup;

    public StandupResponse() {
    }

    public StandupResponse(String standup) {
        this.standup = standup;
    }

    public String getStandup() {
        return standup;
    }

    public void setStandup(String standup) {
        this.standup = standup;
    }
}