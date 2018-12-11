package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Relationships.BUSkill;

public interface BUSkillRepo extends Neo4jRepository<BUSkill, Long> {

    //Adds skill and the skill's category to a BU's skills list, skill and BU must exist within system
    @Query("MATCH (s:Skill {name: {skillName}}) MATCH (bu:BusinessUnit {name: {buName}}) MATCH (s)-[:SKILL_OF_CATEGORY]->(cs:Category) MERGE (bu)-[r:BU_SKILL]->(s)" +
            " MERGE (bu)-[:CATEGORY_BU_SKILL]->(cs)")
    void addSkillToBU (@Param("skillName") String skillName, @Param("buName") String buName);
}
