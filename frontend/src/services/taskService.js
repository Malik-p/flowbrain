import api from "./axios";

export const getTasks = async () => {

    const project = JSON.parse(
        localStorage.getItem("selectedProject")
    );

    const response = await api.get(
        `/projects/${project.id}/tasks`
    );

    return response.data;
};

export const createTask = async (data) => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const project = JSON.parse(
        localStorage.getItem("selectedProject")
    );

    const response = await api.post(
        `/workspaces/${workspace.id}/projects/${project.id}/tasks`,
        data
    );

    return response.data;
};

export const updateTask = async (id, data) => {

    const response = await api.put(
        `/tasks/${id}`,
        data
    );

    return response.data;
};

export const deleteTask = async (id) => {

    const response = await api.delete(
        `/tasks/${id}`
    );

    return response.data;
};