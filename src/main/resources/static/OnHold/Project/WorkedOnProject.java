import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Project;

@RelationshipEntity(type = "WORKED_ON_PROJECT")
public class WorkedOnProject {

    @GeneratedValue@Id private Long id;
    @Property private boolean isActive;
    @StartNode private Employee employee;
    @EndNode private Project project;

    public WorkedOnProject(boolean isActive, Employee employee, Project project) {
        this.isActive = isActive;
        this.employee = employee;
        this.project = project;
    }

    public WorkedOnProject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
