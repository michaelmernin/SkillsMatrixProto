package prototype_skills.prototypeskills.Relationships;

import org.neo4j.ogm.annotation.*;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Skill;

@RelationshipEntity(type = "CATEGORY_BU_SKILL")
public class CategoryBUSkill {

    @GeneratedValue
    @Id
    private Long id;
    //@Property private boolean isEssential;
    @StartNode
    private BusinessUnit businessUnit;
    @EndNode
    private CategorySkill categorySkill;

    public CategoryBUSkill(BusinessUnit businessUnit, CategorySkill categorySkill) {

        this.businessUnit = businessUnit;
        this.categorySkill = categorySkill;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public CategorySkill getCategorySkill() {
        return categorySkill;
    }

    public void setCategorySkill(CategorySkill categorySkill) {
        this.categorySkill = categorySkill;
    }

    public CategoryBUSkill() {

    }


}
