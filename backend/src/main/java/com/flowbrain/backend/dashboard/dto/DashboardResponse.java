package com.flowbrain.backend.dashboard.dto;

public class DashboardResponse {

    private Long totalWorkspaces;
    private Long totalProjects;
    private Long totalTasks;
    private Long completedTasks;
    private Long inProgressTasks;
    private Long todoTasks;

    public Long getTotalWorkspaces() {
        return totalWorkspaces;
    }

    public void setTotalWorkspaces(Long totalWorkspaces) {
        this.totalWorkspaces = totalWorkspaces;
    }

    public Long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(Long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public Long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(Long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public Long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public Long getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(Long inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public Long getTodoTasks() {
        return todoTasks;
    }

    public void setTodoTasks(Long todoTasks) {
        this.todoTasks = todoTasks;
    }

}