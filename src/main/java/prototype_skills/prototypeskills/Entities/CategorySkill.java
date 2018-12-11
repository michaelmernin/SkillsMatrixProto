package prototype_skills.prototypeskills.Entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Category")
public class CategorySkill {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    public CategorySkill(String name) {
        this.name = name;
    }

    public CategorySkill() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
