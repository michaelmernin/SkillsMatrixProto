package prototype_skills.prototypeskills.Entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NodeEntity(label = "Skill")
public class Skill {

    @GeneratedValue@Id private Long id;

    private String name;
    private String resourceLinks;
    private String description;
    private String technology;



    public Skill(String name, String resourceLinks, String description, String technology) {
        this.name = name;
        this.resourceLinks = resourceLinks;
        this.description = description;
        this.technology = technology;
    }

    public Skill() {
    }

    public void setResourceLinks(String resourceLinks) {
        this.resourceLinks = resourceLinks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceLinks() {
        return resourceLinks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
}

