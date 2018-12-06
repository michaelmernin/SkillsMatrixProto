package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.BusinessUnit;

import java.util.List;

public interface BusinessUnitRepository extends Neo4jRepository<BusinessUnit, Long> {

    BusinessUnit findByName(String name);
    List<BusinessUnit> findAll();

    //returns BU of specified Employee
    @Query("MATCH (bu:BusinessUnit)<-[r:EMPLOYEE_OF_BU]-(e:Employee {name: {employeeName}}) RETURN bu")
    BusinessUnit findByEmployee (@Param("employeeName") String employeeName);



}
