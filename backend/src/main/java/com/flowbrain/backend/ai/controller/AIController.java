package com.flowbrain.backend.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flowbrain.backend.ai.dto.AIRequest;
import com.flowbrain.backend.ai.dto.ChatResponse;
import com.flowbrain.backend.ai.dto.ProjectHealthResponse;
import com.flowbrain.backend.ai.dto.SprintSummaryResponse;
import com.flowbrain.backend.ai.dto.StandupResponse;
import com.flowbrain.backend.ai.dto.SuggestionResponse;
import com.flowbrain.backend.ai.dto.TaskBreakdownResponse;
import com.flowbrain.backend.ai.dto.generated.GeneratedTaskResponse;
import com.flowbrain.backend.ai.service.AIService;
import com.flowbrain.backend.common.dto.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ai")
public class AIController {

        private final AIService aiService;

        public AIController(AIService aiService) {
                this.aiService = aiService;
        }

        @PostMapping("/generate-task")
        public ResponseEntity<ApiResponse<GeneratedTaskResponse>> generateTask(

                        @Valid @RequestBody AIRequest request) {

                GeneratedTaskResponse response = aiService.generateTask(
                                request.getWorkspaceId(),
                                request.getPrompt());

                ApiResponse<GeneratedTaskResponse> apiResponse = new ApiResponse<>(
                                true,
                                "AI Response Generated Successfully",
                                response);

                return ResponseEntity.ok(apiResponse);
        }

        @PostMapping("/sprint-summary")
        public ResponseEntity<ApiResponse<SprintSummaryResponse>> generateSprintSummary(

                        @RequestBody AIRequest request) {

                SprintSummaryResponse response = aiService.generateSprintSummary(
                                request.getWorkspaceId());

                return ResponseEntity.ok(

                                new ApiResponse<>(

                                                true,

                                                "Sprint Summary Generated",

                                                response));

        }

        @PostMapping("/project-health")
        public ResponseEntity<ApiResponse<ProjectHealthResponse>> generateProjectHealth(

                        @RequestBody AIRequest request) {

                ProjectHealthResponse response = aiService.generateProjectHealth(
                                request.getWorkspaceId());

                return ResponseEntity.ok(

                                new ApiResponse<>(

                                                true,

                                                "Project Health Generated Successfully",

                                                response));

        }

        @PostMapping("/standup")
        public ResponseEntity<ApiResponse<StandupResponse>> generateStandup(

                        @RequestBody AIRequest request) {

                StandupResponse response = aiService.generateStandup(
                                request.getWorkspaceId());

                return ResponseEntity.ok(

                                new ApiResponse<>(

                                                true,

                                                "Standup Generated Successfully",

                                                response));

        }

        @PostMapping("/task-breakdown")
        public ResponseEntity<ApiResponse<TaskBreakdownResponse>> generateTaskBreakdown(

                        @RequestBody AIRequest request) {

                TaskBreakdownResponse response = aiService.generateTaskBreakdown(

                                request.getWorkspaceId(),

                                request.getPrompt());

                return ResponseEntity.ok(

                                new ApiResponse<>(

                                                true,

                                                "Task Breakdown Generated Successfully",

                                                response));

        }

        @PostMapping("/suggestions")
        public ResponseEntity<ApiResponse<SuggestionResponse>> generateSuggestions(

                        @RequestBody AIRequest request) {

                SuggestionResponse response = aiService.generateSuggestions(
                                request.getWorkspaceId());

                return ResponseEntity.ok(

                                new ApiResponse<>(

                                                true,

                                                "Suggestions Generated Successfully",

                                                response));

        }

        @PostMapping("/chat")
        public ResponseEntity<ApiResponse<ChatResponse>> chat(

                        @RequestBody AIRequest request) {

                ChatResponse response = aiService.chat(

                                request.getWorkspaceId(),

                                request.getPrompt());

                return ResponseEntity.ok(

                                new ApiResponse<>(

                                                true,

                                                "AI Response Generated Successfully",

                                                response));

        }

}