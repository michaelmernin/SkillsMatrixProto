package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.data.repository.query.Param;

import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface HasSkillRepository extends Neo4jRepository<HasSkill, Long> {


    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN properties(r)")
    Map<String, String> getHasSkillDetails(@Param("employeeName") String employeeName, @Param("skillName") String skillName);

//    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN r.expertise")
//    List<String> getHasSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName);

    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) set r.expertise = {expertise} return r")
    HasSkill editHasSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName, @Param("expertise") String expertise);

//    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL {expertise: {expertise}, descriptionOfExpertise: {description}}]-(e:Employee {name: {employeeName}}) set r.expertise = {expertise}")
//    HasSkill editHasSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName, @Param("expertise") String expertise,
//                          @Param("description") String description);

}
