package com.danest.backend.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.danest.backend.domain.Project;
import com.danest.backend.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProject(Long id) {
        return this.projectRepository.findById(id).get();
    }

    public void saveProject(Project newProject) {
        this.projectRepository.save(newProject);
    }

    public Iterable<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }

    public void deleteProject(Long id) {
        this.projectRepository.deleteById(id);
    }

    @Transactional
    public void updateProject(Long id, Map<String, String> partialProject) {
        this.projectRepository.findById(id).get().updateFromMap(partialProject);
    }

}
