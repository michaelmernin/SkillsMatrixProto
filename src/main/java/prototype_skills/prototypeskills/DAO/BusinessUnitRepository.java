package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;

import java.util.List;

public interface BusinessUnitRepository extends Neo4jRepository<BusinessUnit, Long> {

    BusinessUnit findByName(String name);
    List<BusinessUnit> findAll();


}
