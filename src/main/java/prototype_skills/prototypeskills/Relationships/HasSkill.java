package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import prototype_skills.prototypeskills.DAO.Rels.HasSkillRepository;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.HashMap;

@RelationshipEntity(type = "HAS_SKILL")
public class HasSkill {


    @GeneratedValue@Id private Long id;
    @Property private String expertise;
    @Property private String descriptionOfExpertise;
    //@Property private HashMap<String, String>
    @StartNode private Employee employee;
    @EndNode private Skill skill;


    public HasSkill (String expertise, String descriptionOfExpertise, Employee employee, Skill skill) {
        this.expertise = expertise;
        this.descriptionOfExpertise = descriptionOfExpertise;
        this.employee = employee;
        this.skill = skill;
        //return this;
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
