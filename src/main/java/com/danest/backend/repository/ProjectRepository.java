package com.danest.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.danest.backend.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}
