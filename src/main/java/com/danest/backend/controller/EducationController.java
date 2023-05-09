package com.danest.backend.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Education;
import com.danest.backend.dto.EducationDto;
import com.danest.backend.repository.EducationRepository;
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
    void saveEducation(@RequestPart MultipartFile logo, @RequestPart Education education) {
        this.educationService.saveEducation(education);
    }

    @GetMapping
    Iterable<Education> getEducations() {
        return this.educationService.getAllEducations();
    }

}
