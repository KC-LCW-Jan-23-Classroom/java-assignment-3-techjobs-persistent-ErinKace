package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.data.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.techjobs.persistent.models.Skill;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillsController {
    @Autowired
    private SkillsRepository skillsRepository;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("skills", skillsRepository.findAll());
        return "skills/index";

    }
    @GetMapping("add")
    private String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }
    @PostMapping("add")
    private String processAddSkillForm(@ModelAttribute @Valid Skill newSkill, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "skills/add";
        }
        skillsRepository.save(newSkill);
        return "skills/view";
    }
    @GetMapping("view/{skillId}")
    private String displayViewSkill(Model model, @PathVariable int skillsId) {
        Optional<Skill> result = skillsRepository.findById(skillsId);
        if (result.isPresent()) {
            Skill skill = (Skill) result.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        }
        return "redirect:../";
    }
}
