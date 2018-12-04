package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Relationships.EmployeeOfBU;

public interface EmployeeOfBURepo extends Neo4jRepository<EmployeeOfBU, Long> {
}
