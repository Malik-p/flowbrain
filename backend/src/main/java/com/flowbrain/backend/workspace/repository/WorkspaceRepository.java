package com.flowbrain.backend.workspace.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flowbrain.backend.workspace.entity.Workspace;

public interface WorkspaceRepository extends MongoRepository<Workspace, String> {

    List<Workspace> findByMembers_Id(String userId);
    
}
