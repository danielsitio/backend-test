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
import org.springframework.web.bind.annotation.RestController;

import com.danest.backend.domain.Skill;
import com.danest.backend.service.SkillService;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin
public class SkillController {

    private SkillService skillService;

    SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    Iterable<Skill> getAll() {
        return this.skillService.getAllSkills();
    }

    @PostMapping
    Skill addSkill(@RequestBody Skill newSkill) {
        this.skillService.saveSkill(newSkill);
        return this.skillService.getSkill(newSkill.getId());
    }

    @DeleteMapping("/{id}")
    void deleteSkill(@PathVariable Long id) {
        this.skillService.deleteSkill(id);
    }

    @PatchMapping("/{id}")
    Skill patchSkill(@PathVariable Long id, @RequestBody Map<String, String> partialSkill) {
        this.skillService.updateSkill(id, partialSkill);
        return this.skillService.getSkill(id);
    }
}
