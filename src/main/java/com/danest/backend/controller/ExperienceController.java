package com.danest.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Experience;
import com.danest.backend.service.ExperienceService;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin
public class ExperienceController {

    private final ExperienceService experienceService;

    ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    Iterable<Experience> getAll() {
        return this.experienceService.getAllExperiences();
    }

    @PostMapping
    Experience add(@RequestPart Experience experience, @RequestPart(required = false) MultipartFile workplaceLogo)
            throws Exception {
        this.experienceService.saveExperience(experience, workplaceLogo);
        return this.experienceService.getExperience(experience.getId());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        this.experienceService.deleteExperience(id);
    }

    @PatchMapping("/{id}")
    Experience patch(@PathVariable Long id, @RequestPart Experience updatedExperience,
            @RequestPart(required = false) MultipartFile updatedWorkplaceLogo) throws Exception {
        this.experienceService.updateExperience(id, updatedExperience, updatedWorkplaceLogo);
        return this.experienceService.getExperience(id);
    }
}
