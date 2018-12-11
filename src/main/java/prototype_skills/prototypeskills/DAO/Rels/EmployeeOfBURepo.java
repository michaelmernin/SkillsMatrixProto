package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Relationships.EmployeeOfBU;

public interface EmployeeOfBURepo extends Neo4jRepository<EmployeeOfBU, Long> {

    //Creates employee and makes connection to their BU, bu must be specified and exist within system
    @Query("MATCH (bu:BusinessUnit {name: {buName}}) MERGE (e:Employee {name: {employeeName}, location: {locationName}, role: {roleName}}) MERGE (e)-[r:EMPLOYEE_OF_BU]->(bu) return bu")
    BusinessUnit addEmployee (@Param("employeeName") String employeeName, @Param("locationName") String locationName, @Param("roleName") String roleName,
                              @Param("buName") String buName);
}
