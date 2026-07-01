package com.flowbrain.backend.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.flowbrain.backend.project.entity.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}