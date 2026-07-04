package com.flowbrain.backend.task.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flowbrain.backend.task.entity.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByProjectId(String projectId);

    List<Task> findByWorkspaceId(String workspaceId);

}