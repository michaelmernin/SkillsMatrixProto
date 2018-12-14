package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.data.repository.query.Param;

import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface HasSkillRepository extends Neo4jRepository<HasSkill, Long> {


    //Adds skill and the skill's category to an employee's skills list, skill and employee must exist within system
    @Query("MATCH (s:Skill {name: {skillName}}) MATCH (e:Employee {name: {employeeName}}) MATCH (s)-[cr:SKILL_OF_CATEGORY]->(c:Category) MERGE (e)-[has:HAS_SKILL {expertise: {expertise}, expertiseDescription: {expertiseDescription}}]->(s)" +
            " MERGE (e)-[:HAS_CATEGORY_SKILL]->(c)")
    void addSkillToEmployee (@Param("skillName") String skillName, @Param("expertise") String expertise, @Param("expertiseDescription") String expertiseDescription,
                             @Param("employeeName") String employeeName);

    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN properties(r)")
    Map<String, String> getHasSkillDetails(@Param("employeeName") String employeeName, @Param("skillName") String skillName);

//    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN r.expertise")
//    List<String> getHasSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName);

    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) set r.expertise = {expertise} return r")
    HasSkill editHasSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName, @Param("expertise") String expertise);

//    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL {expertise: {expertise}, descriptionOfExpertise: {description}}]-(e:Employee {name: {employeeName}}) set r.expertise = {expertise}")
//    HasSkill editHasSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName, @Param("expertise") String expertise,
//                          @Param("description") String description);

    @Query("MATCH (s:Skill {name: {skillName}})<-[has:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN has")
    HasSkill getSkillRelationship(@Param("employeeName") String employeeName, @Param("skillName") String skillName);

    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)" +
            "MATCH (s)-[cr:SKILL_OF_CATEGORY]->(c:Category)" +
            "MATCH path = (e)-[:HAS_CATEGORY_SKILL]->(c)<-[cr]-(s)" +
            "with collect(path) as paths call apoc.convert.toTree(paths) yield value return value")
    List<HashMap<String, String>> getEmployeeSkillTree (@Param("employeeName") String employeeName);

}
