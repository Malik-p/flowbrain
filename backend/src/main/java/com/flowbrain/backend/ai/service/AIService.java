package com.flowbrain.backend.ai.service;

import com.flowbrain.backend.ai.dto.generated.GeneratedTaskResponse;

public interface AIService {

    GeneratedTaskResponse generateTask(
        String workspaceId,
        String feature);

}