package com.flowbrain.backend.ai.dto;

import java.util.List;

public class MistralResponse {

    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}