package com.flowbrain.backend.ai.dto;

public class SuggestionResponse {

    private String suggestions;

    public SuggestionResponse() {
    }

    public SuggestionResponse(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}
