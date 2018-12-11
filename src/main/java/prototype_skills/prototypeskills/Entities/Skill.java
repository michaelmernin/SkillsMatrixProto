package prototype_skills.prototypeskills.Entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Map;

@NodeEntity(label = "Skill")
public class Skill {

    @GeneratedValue@Id private Long id;

    @Property
    private String name;


    private Map<String, String> resourceLinks;
    private String description;
    private String technology;


    public Skill(String name) {
        this.name = name;
    }

    public Skill() {
    }


    public Map<String, String> getResourceLinks() {
        return resourceLinks;
    }

    public void setResourceLinks(Map<String, String> resourceLinks) {
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

