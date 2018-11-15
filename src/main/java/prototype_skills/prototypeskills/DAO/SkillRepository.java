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
    //Set<Skill> findAllBy;

    @Query("MATCH (s:Skill)<-[r:HAS_SKILL]-(e:Employee {name: {name}}) RETURN s")
    Collection<Skill> skillsList(@Param("name") String name);




    //List<Skill> findByEm
}
