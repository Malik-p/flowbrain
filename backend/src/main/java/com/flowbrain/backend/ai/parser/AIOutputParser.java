package com.flowbrain.backend.ai.parser;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowbrain.backend.ai.dto.generated.GeneratedTaskResponse;

@Component
public class AIOutputParser {

    private final ObjectMapper objectMapper;

    public AIOutputParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public GeneratedTaskResponse parseTaskResponse(String response) {

        try {

            return objectMapper.readValue(
                    response,
                    GeneratedTaskResponse.class);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse AI response");
        }

    }

}