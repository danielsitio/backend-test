package com.danest.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.danest.backend.domain.Education;
import com.danest.backend.service.EducationService;

@RestController
@RequestMapping("/api/educations")
@CrossOrigin
public class EducationController {

    Logger logger = LoggerFactory.getLogger(EducationController.class);

    private EducationService educationService;

    EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    Education saveEducation(@RequestPart(required = false) MultipartFile schoolLogo, @RequestPart Education education)
            throws Exception {
        this.educationService.saveEducation(education, schoolLogo);
        return this.educationService.getEducation(education.getId());
    }

    @GetMapping
    Iterable<Education> getEducations() {
        return this.educationService.getAllEducations();
    }

    @DeleteMapping("/{id}")
    void deleteEducation(@PathVariable Long id) {
        this.educationService.deleteEducation(id);
    }

    @PatchMapping("/{id}")
    Education patchEducation(@PathVariable Long id, @RequestPart Education updatedEducation,
            @RequestPart(required = false) MultipartFile updatedSchoolLogo) throws Exception {
        this.educationService.modifyEducation(id, updatedEducation, updatedSchoolLogo);
        return this.educationService.getEducation(id);
    }

}
