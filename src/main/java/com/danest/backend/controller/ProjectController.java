package com.danest.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Project;
import com.danest.backend.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    Iterable<Project> getAll() {
        return this.projectService.getAllProjects();
    }

    @PostMapping
    Project post(@RequestPart(required = false) MultipartFile projectPortrait, @RequestPart Project project)
            throws Exception {
        this.projectService.saveProject(project, projectPortrait);
        return this.projectService.getProject(project.getId());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        this.projectService.deleteProject(id);
    }

    @PatchMapping("/{id}")
    Project patch(@PathVariable Long id, @RequestPart Project updatedProject,
            @RequestPart(required = false) MultipartFile updatedProjectPortrait) throws Exception {
        this.projectService.updateProject(id, updatedProject, updatedProjectPortrait);
        return this.projectService.getProject(id);
    }

}
