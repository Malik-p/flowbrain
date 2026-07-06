import api from "./axios";

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