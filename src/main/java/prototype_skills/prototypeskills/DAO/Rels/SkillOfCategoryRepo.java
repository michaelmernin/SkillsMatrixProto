package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Relationships.SkillOfCategory;

public interface SkillOfCategoryRepo extends Neo4jRepository<SkillOfCategory, Long> {

    //Might not need, since these will be handled automatically upon creation??
}
