package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "NEEDS_SKILL")
public class NeedsSkill {

    @GeneratedValue@Id private Long id;

    @StartNode private Employee employee;
    @EndNode private Skill skill;

    public NeedsSkill(Employee employee, Skill skill) {
        this.employee = employee;
        this.skill = skill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public NeedsSkill() {

    }
}
