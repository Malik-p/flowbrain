import { useMutation } from "@tanstack/react-query";
import * as aiService from "@/services/aiService";

export function useGenerateTask() {
  return useMutation({
    mutationFn: aiService.generateTask,
  });
}

export function useChat() {
  return useMutation({
    mutationFn: aiService.chat,
  });
}

export function useSprintSummary() {
  return useMutation({
    mutationFn: aiService.generateSprintSummary,
  });
}

export function useProjectHealth() {
  return useMutation({
    mutationFn: aiService.generateProjectHealth,
  });
}

export function useStandup() {
  return useMutation({
    mutationFn: aiService.generateStandup,
  });
}

export function useSuggestions() {
  return useMutation({
    mutationFn: aiService.generateSuggestions,
  });
}