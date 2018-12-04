package prototype_skills.prototypeskills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.Skill;

@Controller
public class SkillsController {

    @Autowired
    SkillRepository skillRepository;

    @PostMapping(path = "/addSkill")
    public String addSkill(String name, String resourceLinks, String description, String technology){

        Skill skill = new Skill(name, resourceLinks, description, technology);
        skillRepository.save(skill);

        return "addSkillsPage";
    }
}
