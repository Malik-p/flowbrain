import api from "./axios";

export const getMembers = async () => {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const response = await api.get(
        `/workspaces/${workspace.id}/members`
    );

    return response.data;

};