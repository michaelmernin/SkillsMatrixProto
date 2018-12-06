package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.Collection;

public interface CategorySkillRepository extends Neo4jRepository<CategorySkill, Long> {

    CategorySkill findByName(String name);


    //returns the category of a skill
    @Query("MATCH (:SKILL {name: {skillName}})-[r:SKILL_OF_CATEGORY]->(cs:SKILL :CATEGORY) RETURN cs")
    CategorySkill findCategoryBySkill(@Param("skillName") String skillName);

    //returns list of category skills tree for a BU
    @Query("MATCH (cs:Skill :Category)<-[r:CATEGORY_BU_SKILL]-(e:BusinessUnit {name: {buName}}) RETURN s")
    Collection<CategorySkill> buSkillsList(@Param("buName") String buName);

}
