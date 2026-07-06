import api from "./axios";

export const generateTask = async (data) => {
  const response = await api.post("/ai/generate-task", data);
  return response.data;
};

export const generateSprintSummary = async (workspaceId) => {
  const response = await api.post("/ai/sprint-summary", {
    workspaceId,
  });
  return response.data;
};

export const generateProjectHealth = async (workspaceId) => {
  const response = await api.post("/ai/project-health", {
    workspaceId,
  });
  return response.data;
};

export const generateStandup = async (workspaceId) => {
  const response = await api.post("/ai/standup", {
    workspaceId,
  });
  return response.data;
};

export const generateSuggestions = async (workspaceId) => {
  const response = await api.post("/ai/suggestions", {
    workspaceId,
  });
  return response.data;
};

export const chat = async (data) => {
  const response = await api.post("/ai/chat", data);
  return response.data;
};