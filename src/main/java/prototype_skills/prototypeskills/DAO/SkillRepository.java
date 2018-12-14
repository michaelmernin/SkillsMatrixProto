package prototype_skills.prototypeskills.DAO;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.*;

public interface SkillRepository extends Neo4jRepository<Skill, Long> {

    Skill findByName(String name);
    Skill findByTechnology(String technology);


    //Adds skill to system according to it's category, category needs to be specified and exist
    @Query("MATCH (cs:Category {name: {categorySkillName}}) MERGE (s:Skill {name: {skillName}}) MERGE (s)-[r:SKILL_OF_CATEGORY]->(cs) return cs")
    CategorySkill addSkill(@Param("skillName") String skillName, @Param("categorySkillName") String categorySkillName);

    @Query("MATCH (s:Skill)<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN s")
    Collection<Skill> skillsList(@Param("employeeName") String employeeName);


    //!!Working Version
    //Returns list of skills in the BU skill tree that the employee DOES NOT HAVE
    @Query("MATCH (p:BusinessUnit {name: {buName}})-[needs:BU_SKILL]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s.name")
    List<String> employeeNeedsBUSkills(@Param("employeeName") String employeeName, @Param("buName") String buName);

    //Practice
//    @Query("MATCH (p:BusinessUnit {name: {buName}})-[needs:BU_SKILL]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s.name")
//    Map<String, String> employeeNeedsBUSkills(@Param("employeeName") String employeeName, @Param("buName") String buName);


    //!!Working version
    //Returns list of skills in the BU skill tree that the employee DOES HAVE s.name, properties(has)
//    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)<-[r:BU_SKILL]-(bu:BusinessUnit {name: {buName}}) RETURN {Skill: s.name, Expertise: has.expertise} as SkillInfo")
//    Map<String,String> employeeHasBUSkills(@Param("employeeName") String employeeName, @Param("buName") String buName);

    //!!!Works to return json formatted path apoc.convert.toTree
//    @Query("MATCH (s:Skill)-[cr:SKILL_OF_CATEGORY]->(c:Category)<-[r:CATEGORY_BU_SKILL]-(bu:BusinessUnit {name: {buName}})" +
//            "MATCH path = (c)<-[cr]-(s) " +
//            "with collect(path) as paths call apoc.convert.toTree(paths) yield value return value")
//    List<HashMap<String, String>> employeeHasBUSkills(@Param("buName") String buName);

    @Query("MATCH (s:Skill)-[cr:SKILL_OF_CATEGORY]->(c:Category)<-[r:CATEGORY_BU_SKILL]-(bu:BusinessUnit {name: {buName}})" +
            "MATCH path = (c)<-[cr]-(s) " +
            "with collect(path) as paths call apoc.convert.toTree(paths) yield value return value")
//    List<ObjectNode> employeeHasBUSkills(@Param("buName") String buName);
    List<HashMap<String, String>> employeeHasBUSkills(@Param("buName") String buName);



    //returns list of skill tree for a BU
//    @Query("MATCH (s:Skill)<-[r:BU_SKILL]-(e:BusinessUnit {name: {buName}}) RETURN s")
//    Collection<Skill> buSkillsList(@Param("buName") String buName);

    //returns list of categories for a BU
    @Query("MATCH (s:Skill)-[:SKILL_OF_CATEGORY]->(c:Category)<-[r:CATEGORY_BU_SKILL]-(e:BusinessUnit {name: {buName}}) RETURN c, s")
    Collection<Skill> buCategoryList(@Param("buName") String buName);


    //tests nested json return objects using apoc.convert.toTree(paths)
    @Query("MATCH path = (s:Skill)<-[]-(bu:BusinessUnit {name: {buName}})" +
            "with collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value")
    Map<String,String> buSkillTree(@Param("employeeName") String employeeName, @Param("buName") String buName);



//    Returns nested path layout
//    MATCH path = (s:Skill)-[:SKILL_OF_CATEGORY]->(:Category)<-[:CATEGORY_BU_SKILL]-(bu:BusinessUnit {name: "Minneapolis"}) with collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value

//    //returns list of categories and skills for a BU
//    @Query("MATCH (s:Skill)<-[r:BU_SKILL]-(e:BusinessUnit {name: {buName}}) MATCH (c:Category)<-[r:CATEGORY_BU_SKILL]-(e)  RETURN c, s")
//    Collection<Skill> buFullList(@Param("buName") String buName);

    @Query("Match (s:Skill)-[cs:SKILL_OF_CATEGORY]->(c:Category)<-[r:CATEGORY_BU_SKILL]-(e:BusinessUnit {name: {buName}})" +
            "MATCH path = (c)<-[cs]-(s) with collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value")
    List<HashMap<String, String>> findBUSkillTree(@Param("buName") String buName);


    //returns list skills associated with the category specified
    @Query("Match (cs:Skill :Category {name: {categorySkillName}})<-[r:SKILL_OF_CATEGORY]-(s) return s")
    Collection<Skill> findSkillsByCategory(@Param("categorySkillName") String categorySkillName);

    //returns nested json tree of skills, with Category as parent
    @Query("Match (s:Skill)-[cr:SKILL_OF_CATEGORY]->(c:Category {name: {categorySkillName}})" +
            "Match path = (c)<-[cr]-(s)" +
            "with collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value")
    List<HashMap<String, String>> findSkillsByCategoryReturnJson (@Param("categorySkillName") String categorySkillName);


}
