package prototype_skills.prototypeskills.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.CategorySkillRepository;
import prototype_skills.prototypeskills.DAO.Rels.SkillOfCategoryRepo;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.SkillOfCategory;

@Controller
public class CategorySkillController {

    @Autowired
    CategorySkillRepository categorySkillRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillOfCategoryRepo skillOfCategoryRepo;

    @PostMapping(path = "/addCategorySkill")
    public String addCategorySkill(String skillName){

        CategorySkill categorySkill = new CategorySkill(skillName);
        categorySkillRepository.save(categorySkill);


        return "index";
    }

    @PostMapping(path = "/addSkillToCategory")
    public String addSkillToCategory(String skillName, String categoryName){
        Skill skill = skillRepository.findByName(skillName);
        CategorySkill categorySkill = categorySkillRepository.findByName(categoryName);
        SkillOfCategory skillOfCategory = new SkillOfCategory(skill, categorySkill);
        skillOfCategoryRepo.save(skillOfCategory);

        return "";
    }
}
