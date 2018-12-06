package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Employee;

@RelationshipEntity(type = "HAS_CATEGORY_SKILL")
public class HasCategorySkill {

    @GeneratedValue@Id private Long id;

    @StartNode private Employee employee;

    @EndNode private CategorySkill categorySkill;

    public HasCategorySkill(Employee employee, CategorySkill categorySkill) {
        this.employee = employee;
        this.categorySkill = categorySkill;
    }

    public HasCategorySkill() {
    }

}
