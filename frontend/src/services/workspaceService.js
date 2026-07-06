import api from "./axios";

export const createWorkspace = async (data) => {
  const response = await api.post(
    "/workspaces",

    data,
  );

  return response.data;
};
export const getWorkspaces = async () => {
  const response = await api.get("/workspaces");

  return response.data;
};

export const getWorkspace = async (id) => {
  const response = await api.get(`/workspaces/${id}`);

  return response.data;
};

export const updateWorkspace = async (id, data) => {
  const response = await api.put(`/workspaces/${id}`, data);

  return response.data;
};

export const deleteWorkspace = async (id) => {
  const response = await api.delete(`/workspaces/${id}`);

  return response.data;
};

export const inviteMember = async (workspaceId, data) => {
  const response = await api.post(`/workspaces/${workspaceId}/invite`, data);

  return response.data;
};

export const getMembers = async () => {
  const workspace = JSON.parse(localStorage.getItem("workspace"));

  const response = await api.get(`/workspaces/${workspace.id}/members`);

  return response.data;
};

export const inviteMemberByEmail = async (data) => {
  const workspace = JSON.parse(localStorage.getItem("workspace"));

  const response = await api.post(`/workspaces/${workspace.id}/invite`, data);

  return response.data;
};
