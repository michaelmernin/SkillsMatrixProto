package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SkillRepository extends Neo4jRepository<Skill, Long> {

    Skill findByName(String name);
    Skill findByTechnology(String technology);
//    List<Skill> findAllByEmployee (Employee employee);
    //Set<Skill> findAllBy;

    @Query("MATCH (s:Skill)<-[r:HAS_SKILL]-(e:Employee {name: {name}}) RETURN s")
    Collection<Skill> skillsList(@Param("name") String name);

    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)<-[r:SKILLS_NEEDED_ON_PROJECT]-(p:Project {name: {projectName}}) RETURN s")
    Collection<Skill> employeeHasProjectSkills(@Param("employeeName") String employeeName,
                                               @Param("projectName") String projectName);


    @Query("MATCH (p:Project {name: {projectName}})-[needs:SKILLS_NEEDED_ON_PROJECT]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s")
    Collection<Skill> employeeNeedsProjectSkills(@Param("employeeName") String employeeName,
                                               @Param("projectName") String projectName);

    @Query("MATCH (p:BusinessUnit {name: {buName}})-[needs:FOUNDATIONAL_BU_SKILL]->(s:Skill) where not (s)<-[:HAS_SKILL]-(:Employee {name: {employeeName}}) return s")
    Collection<Skill> employeeNeedsBUSkills(@Param("employeeName") String employeeName,
                                                 @Param("buName") String buName);

    @Query("MATCH (e:Employee {name: {employeeName}})-[has:HAS_SKILL]->(s:Skill)<-[r:FOUNDATIONAL_BU_SKILL]-(p:BusinessUnit {name: {buName}}) RETURN s")
    Collection<Skill> employeeHasBUSkills(@Param("employeeName") String employeeName,
                                               @Param("buName") String buName);


    @Query("MATCH (s:Skill)<-[r:FOUNDATIONAL_BU_SKILL]-(e:BusinessUnit {name: {buName}}) RETURN s")
    Collection<Skill> buSkillsList(@Param("buName") String buName);
    //List<Skill> findByEm
}
