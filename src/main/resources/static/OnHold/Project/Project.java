import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NodeEntity(label = "Project")
public class Project {

    @GeneratedValue@Id private Long id;

    private String name;
    private String location;

    private Map<String, Integer> rolesNeededOnProject;

    @Relationship(type = "SKILLS_NEEDED_ON_PROJECT")
    private Skill projectSkills;


    @Relationship(type = "PROJECT_OWNED_BY_BUSINESSUNIT")
    private Set<BusinessUnit> projectOfBU;

    public Project() {
    }

    public Project(String name, String location) {

        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Integer> getRolesNeededOnProject() {
        return rolesNeededOnProject;
    }

    public void setRolesNeededOnProject(Map<String, Integer> rolesNeededOnProject) {
        this.rolesNeededOnProject = rolesNeededOnProject;
    }

    public Skill getProjectSkills() {
        return projectSkills;
    }

    public void setProjectSkills(Skill projectSkills) {
        this.projectSkills = projectSkills;
    }

    public Set<BusinessUnit> getProjectOfBU() {
        return projectOfBU;
    }

    public void setProjectOfBU(Set<BusinessUnit> projectOfBU) {
        this.projectOfBU = projectOfBU;
    }
}

    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)<-[r:SKILLS_NEEDED_ON_PROJECT]-(p:Project {name: {projectName}}) RETURN s")
    Collection<Skill> employeeHasProjectSkills(@Param("employeeName") String employeeName,
                                               @Param("projectName") String projectName);


    @Query("MATCH (p:Project {name: {projectName}})-[needs:SKILLS_NEEDED_ON_PROJECT]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s")
    Collection<Skill> employeeNeedsProjectSkills(@Param("employeeName") String employeeName,
                                                 @Param("projectName") String projectName);
