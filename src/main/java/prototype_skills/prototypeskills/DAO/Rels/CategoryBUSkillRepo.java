package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Relationships.CategoryBUSkill;

public interface CategoryBUSkillRepo extends Neo4jRepository<CategoryBUSkill, Long> {

}
