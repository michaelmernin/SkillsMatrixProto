package prototype_skills.prototypeskills.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.CategorySkillRepository;
import prototype_skills.prototypeskills.DAO.Rels.SkillOfCategoryRepo;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.SkillOfCategory;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SkillsController {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    CategorySkillRepository categorySkillRepository;

    @Autowired
    SkillOfCategoryRepo skillOfCategoryRepo;

    @PostMapping(path = "/addSkill")
    public String addSkill(Model model, String skillName, String categorySkillName){
        //Skill will be created if it is tied to an existing category
        //May want to check if exists, or else you wouldn't know
        CategorySkill response = skillRepository.addSkill(skillName, categorySkillName);
        if(response == null){
            model.addAttribute("error", categorySkillName + " not in system");
        }

        return "index";
    }

    @GetMapping(path = "/getSkill")
    public String getSkill(Model model, String skillName){

        Skill skill = skillRepository.findByName(skillName);
        model.addAttribute("skillName", skill.getName());

        return "skillsPage";
    }

    @PostMapping(path = "/editSkill")
    public String editSkill(Model model, String skillName, String newName, String resourceKey, String resourceValue){

        Skill skill = skillRepository.findByName(skillName);
        if(newName != null){
            skill.setName(newName);
        }
        if(resourceKey != null){
            Map<String, String> resourceLinks = new HashMap<>();
            resourceLinks.put(resourceKey, resourceValue);
            skill.setResourceLinks(resourceLinks);
        }

        skillRepository.save(skill);

        return "skillsPage";
    }

    @GetMapping(path = "/skills")
    public String skills(Model model){

        return "addSkillsPage";
    }
}
