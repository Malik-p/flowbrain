package com.flowbrain.backend.ai.service;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.ai.client.MistralClient;
import com.flowbrain.backend.ai.context.AIContextBuilder;
import com.flowbrain.backend.ai.dto.generated.GeneratedTaskResponse;
import com.flowbrain.backend.ai.parser.AIOutputParser;
import com.flowbrain.backend.ai.prompt.PromptBuilder;

@Service
public class AIServiceImpl implements AIService {

    private final MistralClient mistralClient;
    private final AIContextBuilder contextBuilder;
    private final PromptBuilder promptBuilder;
    private final AIOutputParser parser;

    public AIServiceImpl(
            MistralClient mistralClient, AIContextBuilder contextBuilder, PromptBuilder promptBuilder,
            AIOutputParser parser) {

        this.mistralClient = mistralClient;
        this.contextBuilder = contextBuilder;
        this.promptBuilder = promptBuilder;
        this.parser = parser;
    }

    @Override
    public GeneratedTaskResponse generateTask(String workspaceId, String feature) {

        String context = contextBuilder.buildWorkspaceContext(workspaceId);

        String prompt = promptBuilder.buildTaskPrompt(
                feature,
                context);

        String response = mistralClient.chat(prompt);

        return parser.parseTaskResponse(
                response);

    }

}
