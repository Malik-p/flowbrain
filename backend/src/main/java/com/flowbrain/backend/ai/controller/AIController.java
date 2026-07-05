package com.flowbrain.backend.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flowbrain.backend.ai.dto.AIRequest;
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
}