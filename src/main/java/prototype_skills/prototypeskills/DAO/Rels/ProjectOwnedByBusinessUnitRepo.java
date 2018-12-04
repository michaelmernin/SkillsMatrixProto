package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Relationships.ProjectOwnedByBU;

public interface ProjectOwnedByBusinessUnitRepo extends Neo4jRepository<ProjectOwnedByBU, Long> {
}
