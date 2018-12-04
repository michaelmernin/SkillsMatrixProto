import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Project;

@RelationshipEntity(type = "PROJECT_OWNED_BY_BUSINESSUNIT")
public class ProjectOwnedByBU {

    @GeneratedValue@Id private Long id;
    @StartNode private Project project;
    @EndNode private BusinessUnit businessUnit;

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

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public ProjectOwnedByBU() {

    }

    public ProjectOwnedByBU(Project project, BusinessUnit businessUnit) {

        this.project = project;
        this.businessUnit = businessUnit;
    }
}
