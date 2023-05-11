package com.danest.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Education;
import com.danest.backend.domain.Image;
import com.danest.backend.repository.EducationRepository;

import jakarta.transaction.Transactional;

@Service
public class EducationService {

    @Value("${custom.image-directory-url}")
    private String imageDirectory;

    private final EducationRepository educationRepository;
    private final StorageService storageService;

    EducationService(EducationRepository educationRepository, StorageService storageService) {
        this.educationRepository = educationRepository;
        this.storageService = storageService;
    }

    public Education getEducation(Long id) {
        return this.educationRepository.findById(id).get();
    }

    @Transactional
    public void saveEducation(Education newEducation, MultipartFile schoolLogo) throws Exception {
        this.educationRepository.save(newEducation);
        if (schoolLogo != null) {
            storageService.store(schoolLogo, "school" + newEducation.getId());
            Image image = new Image(
                    imageDirectory + "school" + newEducation.getId() + storageService.getExtension(schoolLogo));
            newEducation.getSchool().setLogo(image);
        }
    }

    public Iterable<Education> getAllEducations() {
        return this.educationRepository.findAll();
    }

    public void deleteEducation(Long id) {
        Optional<Image> currentImage = educationRepository.findById(id).get().getSchool().getLogo();
        if (currentImage.isPresent())
            storageService.delete(currentImage.get().getName());
        this.educationRepository.deleteById(id);
    }

    @Transactional
    public void modifyEducation(Long id, Education updatedEducation, MultipartFile updatedSchoolLogo) throws Exception {
        Education educationToUpdate = this.educationRepository.findById(id).get();
        if (updatedSchoolLogo != null) {
            storageService.store(updatedSchoolLogo, "school" + educationToUpdate.getId());
            Image image = new Image(imageDirectory + "school" + educationToUpdate.getId()
                    + storageService.getExtension(updatedSchoolLogo));
            updatedEducation.getSchool().setLogo(image);
        } else if (educationToUpdate.getSchool().getLogo().isPresent()) {
            updatedEducation.getSchool().setLogo(educationToUpdate.getSchool().getLogo().get());
        }
        educationToUpdate.copyEducation(updatedEducation);
    }
}
