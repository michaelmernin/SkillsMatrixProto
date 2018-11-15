package prototype_skills.prototypeskills.DAO;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.List;

public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {

    Employee findFirstByName(String name);

    List<Employee> findAll();

    List<Employee> findAllByBusinessUnitWorkingFor(BusinessUnit businessUnit);



}
