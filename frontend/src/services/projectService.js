import api from "./axios";

export const getProjects = async () => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.get(
        `/workspaces/${workspace.id}/projects`
    );

    return response.data;
};

export const createProject = async (data) => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.post(
        `/workspaces/${workspace.id}/projects`,
        data
    );

    return response.data;
};

export const updateProject = async (id, data) => {

    const response = await api.put(
        `/projects/${id}`,
        data
    );

    return response.data;
};

export const deleteProject = async (id) => {

    const response = await api.delete(
        `/projects/${id}`
    );

    return response.data;
};