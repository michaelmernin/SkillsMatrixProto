package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Relationships.FoundationalBUSkill;

public interface FoundationalBUSkillRepo extends Neo4jRepository<FoundationalBUSkill, Long> {
}
