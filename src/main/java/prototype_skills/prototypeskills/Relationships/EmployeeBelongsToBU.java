package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;

@RelationshipEntity(type = "EMPLOYEE_OF_BU")
public class EmployeeBelongsToBU {

    @GeneratedValue@Id private Long id;
    @StartNode private Employee employee;
    @EndNode private BusinessUnit businessUnit;

    public EmployeeBelongsToBU(Employee employee, BusinessUnit businessUnit) {
        this.employee = employee;
        this.businessUnit = businessUnit;
    }

    public EmployeeBelongsToBU() {
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

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }
}
