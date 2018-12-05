package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SkillRepository extends Neo4jRepository<Skill, Long> {

    Skill findByName(String name);
    Skill findByTechnology(String technology);
//    List<Skill> findAllByEmployee (Employee employee);
    //Set<Skill> findAllBy;
    //Skill findById (Long id);

    @Query("MATCH (s:Skill)<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN s")
    Collection<Skill> skillsList(@Param("employeeName") String employeeName);

    @Query("MATCH (s:Skill {name: {skillName}})<-[r:HAS_SKILL]-(e:Employee {name: {employeeName}}) RETURN r")
    HasSkill getSkill(@Param("employeeName") String employeeName, @Param("skillName") String skillName);


    @Query("MATCH (p:BusinessUnit {name: {buName}})-[needs:FOUNDATIONAL_BU_SKILL]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s")
    Collection<Skill> employeeNeedsBUSkills(@Param("employeeName") String employeeName,
                                                 @Param("buName") String buName);

    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)<-[r:FOUNDATIONAL_BU_SKILL]-(p:BusinessUnit {name: {buName}}) RETURN s")
    Collection<Skill> employeeHasBUSkills(@Param("employeeName") String employeeName,
                                               @Param("buName") String buName);


    @Query("MATCH (s:Skill)<-[r:FOUNDATIONAL_BU_SKILL]-(e:BusinessUnit {name: {buName}}) RETURN s")
    Collection<Skill> buSkillsList(@Param("buName") String buName);


}
