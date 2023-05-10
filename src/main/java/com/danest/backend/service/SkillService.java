package com.danest.backend.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.danest.backend.domain.Skill;
import com.danest.backend.repository.SkillRepository;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Iterable<Skill> getAllSkills() {
        return this.skillRepository.findAll();
    }

    public void saveSkill(Skill newSkill) {
        this.skillRepository.save(newSkill);
    }

    public Skill getSkill(Long id) {
        return this.skillRepository.findById(id).get();
    }

    public void deleteSkill(Long id) {
        this.skillRepository.deleteById(id);
    }

    public void updateSkill(Long id, Map<String, String> partialSkill) {
        this.skillRepository.findById(id).get().updateFromMap(partialSkill);
    }

}
