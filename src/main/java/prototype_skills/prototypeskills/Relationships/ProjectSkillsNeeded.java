package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.Project;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "SKILLS_NEEDED_ON_PROJECT")
public class ProjectSkillsNeeded {

    @GeneratedValue@Id private Long id;
    @StartNode private Project project;
    @EndNode private Skill skill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public ProjectSkillsNeeded() {

    }

    public ProjectSkillsNeeded(Project project, Skill skill) {

        this.project = project;
        this.skill = skill;
    }
}
