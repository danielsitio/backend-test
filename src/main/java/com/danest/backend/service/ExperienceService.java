package com.danest.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Experience;
import com.danest.backend.domain.Image;
import com.danest.backend.repository.ExperienceRepository;
import jakarta.transaction.Transactional;

@Service
public class ExperienceService {

    @Value("${custom.image-directory-url}")
    private String imageDirectory;

    private final ExperienceRepository experienceRepository;
    private final StorageService storageService;

    ExperienceService(ExperienceRepository experienceRepository, StorageService storageService) {
        this.experienceRepository = experienceRepository;
        this.storageService = storageService;
    }

    public Experience getExperience(Long id) {
        return this.experienceRepository.findById(id).get();
    }

    @Transactional
    public void saveExperience(Experience newExperience, MultipartFile workplaceLogo) throws Exception {
        this.experienceRepository.save(newExperience);
        if (workplaceLogo != null) {
            storageService.store(workplaceLogo, newExperience.getWorkplaceIdentifier());
            Image image = new Image(imageDirectory + newExperience.getWorkplaceIdentifier()
                    + storageService.getExtension(workplaceLogo));
            newExperience.getWorkplace().setLogo(image);
        }
    }

    public Iterable<Experience> getAllExperiences() {
        return this.experienceRepository.findAll();
    }

    public void deleteExperience(Long id) {
        Optional<Image> currentImage = experienceRepository.findById(id).get().getWorkplace().getLogo();
        if (currentImage.isPresent())
            storageService.delete(currentImage.get().getName());
        this.experienceRepository.deleteById(id);
    }

    @Transactional
    public void updateExperience(Long id, Experience updatedExperience, MultipartFile updatedWorkplaceLogo)
            throws Exception {
        Experience experienceToUpdate = this.experienceRepository.findById(id).get();
        if (updatedWorkplaceLogo != null) {
            storageService.store(updatedWorkplaceLogo, experienceToUpdate.getWorkplaceIdentifier());
            Image image = new Image(imageDirectory + experienceToUpdate.getWorkplaceIdentifier()
                    + storageService.getExtension(updatedWorkplaceLogo));
            updatedExperience.getWorkplace().setLogo(image);
        } else {
            updatedExperience.getWorkplace().setLogo(experienceToUpdate.getWorkplace().getLogo().get());
        }
        this.experienceRepository.findById(id).get().copyExperience(updatedExperience);
    }
}
