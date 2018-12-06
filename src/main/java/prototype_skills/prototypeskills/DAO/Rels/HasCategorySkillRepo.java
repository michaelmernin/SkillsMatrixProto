package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Relationships.HasCategorySkill;

public interface HasCategorySkillRepo extends Neo4jRepository<HasCategorySkill, Long> {
}
