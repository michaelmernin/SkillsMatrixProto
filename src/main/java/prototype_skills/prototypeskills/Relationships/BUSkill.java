package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "BU_SKILL")
public class BUSkill {

    @GeneratedValue@Id private Long id;
    @StartNode
    private BusinessUnit businessUnit;
    @EndNode private Skill skill;


    public BUSkill(BusinessUnit businessUnit, Skill skill) {
        this.businessUnit = businessUnit;
        this.skill = skill;
    }

    public BUSkill() {
    }
}
