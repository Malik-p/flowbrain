import api from "./axios";

// ================= GENERATE TASK =================

export const generateTask = async (data) => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(

        "/ai/generate-task",

        {
            workspaceId: workspace.id,
            prompt: data.prompt
        }

    );

    return response.data;

};

// ================= CHAT =================

export const chat = async (data) => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(

        "/ai/chat",

        {
            workspaceId: workspace.id,
            prompt: data.prompt
        }

    );

    return response.data;

};

// ================= SPRINT SUMMARY =================

export const sprintSummary = async () => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(

        "/ai/sprint-summary",

        {
            workspaceId: workspace.id
        }

    );

    return response.data;

};

// ================= PROJECT HEALTH =================

export const projectHealth = async () => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(

        "/ai/project-health",

        {
            workspaceId: workspace.id
        }

    );

    return response.data;

};

// ================= STANDUP =================

export const standup = async () => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(

        "/ai/standup",

        {
            workspaceId: workspace.id
        }

    );

    return response.data;

};

// ================= SUGGESTIONS =================

export const suggestions = async () => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(

        "/ai/suggestions",

        {
            workspaceId: workspace.id
        }

    );

    return response.data;

};