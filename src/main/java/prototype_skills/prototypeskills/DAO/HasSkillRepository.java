package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.List;
import java.util.Set;

public interface HasSkillRepository extends Neo4jRepository<HasSkill, Long> {

    //HasSkill findByEmployee(Employee employee);

    Set<HasSkill> findAllByEmployee(Employee employee);

}
