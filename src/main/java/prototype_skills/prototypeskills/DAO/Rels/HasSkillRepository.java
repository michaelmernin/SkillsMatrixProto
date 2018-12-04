package prototype_skills.prototypeskills.DAO.Rels;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import prototype_skills.prototypeskills.Relationships.HasSkill;


public interface HasSkillRepository extends Neo4jRepository<HasSkill, Long> {

    //HasSkill findByEmployee(Employee employee);

    //Collection<HasSkill> findAllByEmployee(Employee employee);

}
