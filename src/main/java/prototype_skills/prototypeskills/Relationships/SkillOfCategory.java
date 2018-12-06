package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "SKILL_OF_CATEGORY")
public class SkillOfCategory {

    @GeneratedValue@Id private Long id;

    @StartNode private Skill skill;

    @EndNode private CategorySkill categorySkill;

    public SkillOfCategory(Skill skill, CategorySkill categorySkill) {
        this.skill = skill;
        this.categorySkill = categorySkill;
    }

    public SkillOfCategory() {
    }
}
