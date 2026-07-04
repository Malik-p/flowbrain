package com.flowbrain.backend.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flowbrain.backend.project.entity.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

    List<Project> findByWorkspaceId(String workspaceId);

}