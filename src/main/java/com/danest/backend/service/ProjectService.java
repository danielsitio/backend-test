package com.danest.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Image;
import com.danest.backend.domain.Project;
import com.danest.backend.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    @Value("${custom.image-directory-url}")
    private String imageDirectory;

    private final StorageService storageService;
    private final ProjectRepository projectRepository;

    ProjectService(ProjectRepository projectRepository, StorageService storageService) {
        this.projectRepository = projectRepository;
        this.storageService = storageService;
    }

    public Project getProject(Long id) {
        return this.projectRepository.findById(id).get();
    }

    @Transactional
    public void saveProject(Project newProject, MultipartFile projectPortrait) throws Exception {
        this.projectRepository.save(newProject);
        if (projectPortrait != null) {
            storageService.store(projectPortrait, "project" + newProject.getId());
            Image image = new Image(
                    imageDirectory + "project" + newProject.getId() + storageService.getExtension(projectPortrait));
            newProject.setPortrait(image);
        }
    }

    public Iterable<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }

    public void deleteProject(Long id) {
        Optional<Image> currentImage = projectRepository.findById(id).get().getPortrait();
        if (currentImage.isPresent())
            storageService.delete(currentImage.get().getName());
        this.projectRepository.deleteById(id);
    }

    @Transactional
    public void updateProject(Long id, Project updatedProject, MultipartFile updatedProjectPortrait) throws Exception {
        Project projectToUpdate = projectRepository.findById(id).get();
        if (updatedProjectPortrait != null) {
            storageService.store(updatedProjectPortrait, "project" + id);
            Image image = new Image(
                    imageDirectory + "project" + id + storageService.getExtension(updatedProjectPortrait));
            updatedProject.setPortrait(image);
        } else if (projectToUpdate.getPortrait().isPresent()) {
            updatedProject.setPortrait(projectToUpdate.getPortrait().get());
        }
        projectToUpdate.copyProject(updatedProject);
    }

}
