package prototype_skills.prototypeskills.DAO;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Project;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    Project findByName (String name);
}
