package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "FOUNDATIONAL_BU_SKILL")
public class FoundationalBUSkill {

    @GeneratedValue
    @Id
    private Long id;
    //@Property private boolean isEssential;
    @StartNode
    private BusinessUnit businessUnit;
    @EndNode
    private Skill skill;

    public FoundationalBUSkill(BusinessUnit businessUnit, Skill skill) {

        this.businessUnit = businessUnit;
        this.skill = skill;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public FoundationalBUSkill() {

    }


}
