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

  public String buildSprintPrompt(String context) {

    return """
        You are an Agile Scrum Master.

        Analyze the following sprint information.

        %s

        Generate:

        1. Sprint Summary
        2. Completed Work
        3. Current Progress
        4. Risks
        5. Recommendations

        Keep the response professional and concise.
        """.formatted(context);

  }

  public String buildHealthPrompt(String context) {

    return """
        You are an experienced Engineering Manager.

        Analyze the following project health.

        %s

        Generate:

        1. Overall Health Score
        2. Progress Analysis
        3. Risks
        4. Bottlenecks
        5. Actionable Recommendations

        Keep the report concise and professional.
        """.formatted(context);
  }

  public String buildStandupPrompt(String context) {

    return """
        You are a Scrum Master.

        Using the following project data:

        %s

        Generate a daily standup report including:

        Yesterday's progress

        Today's plan

        Blockers

        Keep it short.
        """.formatted(context);
  }

  public String buildBreakdownPrompt(
      String feature,
      String context) {

    return """
        You are a Senior Software Architect.

        Workspace Details

        %s

        Feature

        %s

        Break this feature into small implementation tasks.

        Mention backend, frontend,
        database and testing tasks separately.
        """.formatted(context, feature);
  }

  public String buildSuggestionPrompt(String context) {

    return """
        You are a Senior Engineering Manager.

        Analyze the following workspace.

        %s

        Suggest:

        Productivity improvements

        Missing features

        Code quality improvements

        Performance improvements

        Keep suggestions practical.
        """.formatted(context);
  }

  public String buildChatPrompt(
      String question,
      String context) {

    return """
        You are FlowBrain AI Assistant.

        Workspace Information

        %s

        User Question

        %s

        Answer only using the available workspace information.

        If information is unavailable,
        politely say so.
        """.formatted(context, question);
  }

}