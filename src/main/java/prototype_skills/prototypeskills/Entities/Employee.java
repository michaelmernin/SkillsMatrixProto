package prototype_skills.prototypeskills.Entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Employee")
public class Employee {

    @GeneratedValue@Id private Long id;

    private String name;
    private  String role;
    private  String location;


    @Relationship(type = "HAS_SKILL")
    private Skill skill;

    @Relationship(type = "HAS_SKILL")
    private CategorySkill categorySkill;

    @Relationship(type = "EMPLOYEE_OF_BU")
    private BusinessUnit businessUnitWorkingFor;


    public Employee(String name, String role, String location) {
        this.name = name;
        this.role = role;
        this.location = location;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


//    public Skill getSkill() {
//        return skill;
//    }
//
//    public void setSkill(Skill skill) {
//        this.skill = skill;
//    }

    public BusinessUnit getBusinessUnitWorkingFor() {
        return businessUnitWorkingFor;
    }

    public void setBusinessUnitWorkingFor(BusinessUnit businessUnitWorkingFor) {
        this.businessUnitWorkingFor = businessUnitWorkingFor;
    }
}
