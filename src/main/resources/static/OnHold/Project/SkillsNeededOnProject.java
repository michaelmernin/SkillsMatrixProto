import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.Project;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "SKILLS_NEEDED_ON_PROJECT")
public class SkillsNeededOnProject {

    @GeneratedValue@Id private Long id;
    @Property private boolean isEssential;
    @StartNode private Project project;
    @EndNode private Skill skill;

    public SkillsNeededOnProject(boolean isEssential, Project project, Skill skill) {
        this.isEssential = isEssential;
        this.project = project;
        this.skill = skill;
    }

    public boolean isEssential() {
        return isEssential;
    }

    public void setEssential(boolean essential) {
        isEssential = essential;
    }

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

    public SkillsNeededOnProject() {

    }


}
