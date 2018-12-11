package prototype_skills.prototypeskills.Entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;

import java.util.List;
import java.util.Optional;

@NodeEntity(label = "Employee")
public class Employee {


    @GeneratedValue@Id private Long id;

    private String name;
    //will be unique
    private String email;
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

}
