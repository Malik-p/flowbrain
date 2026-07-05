package com.flowbrain.backend.ai.service;

import com.flowbrain.backend.ai.dto.ChatResponse;
import com.flowbrain.backend.ai.dto.ProjectHealthResponse;
import com.flowbrain.backend.ai.dto.SprintSummaryResponse;
import com.flowbrain.backend.ai.dto.StandupResponse;
import com.flowbrain.backend.ai.dto.SuggestionResponse;
import com.flowbrain.backend.ai.dto.TaskBreakdownResponse;
import com.flowbrain.backend.ai.dto.generated.GeneratedTaskResponse;

public interface AIService {

    GeneratedTaskResponse generateTask(
            String workspaceId,
            String feature);

    SprintSummaryResponse generateSprintSummary(
            String workspaceId);

    ProjectHealthResponse generateProjectHealth(
            String workspaceId);

    StandupResponse generateStandup(
            String workspaceId);

    TaskBreakdownResponse generateTaskBreakdown(
            String workspaceId,
            String feature);

    SuggestionResponse generateSuggestions(
            String workspaceId);

    ChatResponse chat(
            String workspaceId,
            String question);

}