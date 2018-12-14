package prototype_skills.prototypeskills.DAO;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {

    List<Employee> findAllByName(String name);

    Employee findByName(String name);

    //returns all employees of specified BU
    @Query("MATCH (bu:BusinessUnit {name: {buName}})<-[r:EMPLOYEE_OF_BU]-(e:Employee) RETURN e")
    Collection<Employee> findAllByBU(@Param("buName") String buName);





}
