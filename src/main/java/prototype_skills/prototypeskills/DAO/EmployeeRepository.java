package prototype_skills.prototypeskills.DAO;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {

    Employee findByName(String name);

    //returns all employees of specified BU
    @Query("MATCH (bu:BusinessUnit {name: {buName}})<-[r:EMPLOYEE_OF_BU]-(e:Employee) RETURN e")
    Collection<Employee> findAllByBU(@Param("buName") String buName);

    //Creates employee and makes connection to their BU, bu must be specified and exist within system
    @Query("MATCH (bu:BusinessUnit {name: {buName}}) MERGE (e:Employee {name: {employeeName}, location: {locationName}, role: {roleName}}) MERGE (e)-[r:EMPLOYEE_OF_BU]->(bu) return bu")
    BusinessUnit addEmployee (@Param("employeeName") String employeeName, @Param("locationName") String locationName, @Param("roleName") String roleName,
                              @Param("buName") String buName);

    //Adds skill and the skill's category to an employee's skills list, skill and employee must exist within system
    @Query("MATCH (s:Skill {name: {skillName}}) MATCH (e:Employee {name: {employeeName}}) MATCH (s)-[:SKILL_OF_CATEGORY]->(cs:Skill :Category) MERGE (e)-[r:HAS_SKILL {expertise: {expertise}, expertiseDescription: {expertiseDescription}}]->(s)" +
            " MERGE (e)-[:HAS_CATEGORY_SKILL]->(cs)")
    void addSkillToEmployee (@Param("skillName") String skillName, @Param("expertise") String expertise, @Param("expertiseDescription") String expertiseDescription,
                             @Param("employeeName") String employeeName);


}
