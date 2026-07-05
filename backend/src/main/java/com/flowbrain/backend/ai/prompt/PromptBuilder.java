package com.flowbrain.backend.ai.prompt;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildTaskPrompt(
            String feature,
            String context) {

        return """
                You are an experienced Senior Software Architect.

                Below is the current project context.

                ===========================
                %s
                ===========================

                Generate implementation tasks for:

                %s

                IMPORTANT RULES

                Return ONLY valid JSON.

                Do NOT return markdown.

                Do NOT return explanations.

                Do NOT wrap JSON inside ```.

                Response format:

                {
                  "tasks":[
                    {
                      "title":"",
                      "description":"",
                      "priority":"LOW | MEDIUM | HIGH",
                      "estimatedHours":0,
                      "acceptanceCriteria":""
                    }
                  ]
                }
                """.formatted(
                context,
                feature);
    }

}