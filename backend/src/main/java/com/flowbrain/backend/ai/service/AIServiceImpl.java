package com.flowbrain.backend.ai.service;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.ai.client.MistralClient;
import com.flowbrain.backend.ai.context.AIContextBuilder;
import com.flowbrain.backend.ai.dto.ChatResponse;
import com.flowbrain.backend.ai.dto.ProjectHealthResponse;
import com.flowbrain.backend.ai.dto.SprintSummaryResponse;
import com.flowbrain.backend.ai.dto.StandupResponse;
import com.flowbrain.backend.ai.dto.SuggestionResponse;
import com.flowbrain.backend.ai.dto.TaskBreakdownResponse;
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

    @Override
    public SprintSummaryResponse generateSprintSummary(
            String workspaceId) {

        String context = contextBuilder.buildSprintContext(workspaceId);

        String prompt = promptBuilder.buildSprintPrompt(context);

        String response = mistralClient.chat(prompt);

        return new SprintSummaryResponse(response);

    }

    @Override
    public ProjectHealthResponse generateProjectHealth(
            String workspaceId) {

        String context = contextBuilder.buildHealthContext(
                workspaceId);

        String prompt = promptBuilder.buildHealthPrompt(
                context);

        String response = mistralClient.chat(prompt);

        return new ProjectHealthResponse(response);
    }

    @Override
public StandupResponse generateStandup(
        String workspaceId) {

    String context =
            contextBuilder.buildWorkspaceContext(workspaceId);

    String prompt =
            promptBuilder.buildStandupPrompt(context);

    String response =
            mistralClient.chat(prompt);

    return new StandupResponse(response);
}

@Override
public TaskBreakdownResponse generateTaskBreakdown(
        String workspaceId,
        String feature) {

    String context =
            contextBuilder.buildWorkspaceContext(workspaceId);

    String prompt =
            promptBuilder.buildBreakdownPrompt(
                    feature,
                    context);

    String response =
            mistralClient.chat(prompt);

    return new TaskBreakdownResponse(response);
}

@Override
public SuggestionResponse generateSuggestions(
        String workspaceId) {

    String context =
            contextBuilder.buildWorkspaceContext(workspaceId);

    String prompt =
            promptBuilder.buildSuggestionPrompt(context);

    String response =
            mistralClient.chat(prompt);

    return new SuggestionResponse(response);
}

@Override
public ChatResponse chat(
        String workspaceId,
        String question) {

    String context =
            contextBuilder.buildWorkspaceContext(workspaceId);

    String prompt =
            promptBuilder.buildChatPrompt(
                    question,
                    context);

    String response =
            mistralClient.chat(prompt);

    return new ChatResponse(response);
}
}
