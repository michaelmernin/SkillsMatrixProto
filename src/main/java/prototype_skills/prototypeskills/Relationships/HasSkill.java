package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "HAS_SKILL")
public class HasSkill {

    @GeneratedValue@Id private Long id;
    @Property private String expertise;
    @Property private String descriptionOfExpertise;
    @StartNode private Employee employee;
    @EndNode private Skill skill;

    public HasSkill(String expertise, String descriptionOfExpertise, Employee employee, Skill skill) {
        this.expertise = expertise;
        this.descriptionOfExpertise = descriptionOfExpertise;
        this.employee = employee;
        this.skill = skill;
    }

    public HasSkill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getDescriptionOfExpertise() {
        return descriptionOfExpertise;
    }

    public void setDescriptionOfExpertise(String descriptionOfExpertise) {
        this.descriptionOfExpertise = descriptionOfExpertise;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
