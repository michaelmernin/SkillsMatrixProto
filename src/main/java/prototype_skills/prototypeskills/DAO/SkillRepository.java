package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SkillRepository extends Neo4jRepository<Skill, Long> {

    Skill findByName(String name);
    Skill findByTechnology(String technology);


    //Adds skill to system according to it's category, category needs to be specified and exist
    @Query("MATCH (cs:Skill :Category {name: {categorySkillName}}) MERGE (s:Skill {name: {skillName}}) MERGE (s)-[r:SKILL_OF_CATEGORY]->(cs) return cs")
    CategorySkill addSkill(@Param("skillName") String skillName, @Param("categorySkillName") String categorySkillName);

    @Query("MATCH (s:Skill)<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN s")
    Collection<Skill> skillsList(@Param("employeeName") String employeeName);

    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN r")
    HasSkill getSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName);

    //Returns list of skills in the BU skill tree that the employee DOES NOT HAVE
    @Query("MATCH (p:BusinessUnit {name: {buName}})-[needs:BU_SKILL]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s.name")
    List<String> employeeNeedsBUSkills(@Param("employeeName") String employeeName, @Param("buName") String buName);

    //Returns list of skills in the BU skill tree that the employee DOES HAVE
    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)<-[r:BU_SKILL]-(bu:BusinessUnit {name: {buName}}) RETURN s.name")
    List<String> employeeHasBUSkills(@Param("employeeName") String employeeName, @Param("buName") String buName);

    //returns list of skill tree for a BU
    @Query("MATCH (s:Skill)<-[r:BU_SKILL]-(e:BusinessUnit {name: {buName}}) RETURN s")
    Collection<Skill> buSkillsList(@Param("buName") String buName);



    //returns list skills associated with the category specified
    @Query("Match (cs:Skill :Category {name: {categorySkillName}})<-[r:SKILL_OF_CATEGORY]-(s) return s")
    Collection<Skill> findSkillsByCategory(@Param("categorySkillName") String categorySkillName);


}
