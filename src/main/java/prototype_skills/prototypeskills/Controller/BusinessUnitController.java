package prototype_skills.prototypeskills.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;
import prototype_skills.prototypeskills.DAO.Rels.BUSkillRepo;
import prototype_skills.prototypeskills.DAO.Rels.CategoryBUSkillRepo;
import prototype_skills.prototypeskills.DAO.Rels.EmployeeOfBURepo;

import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.BUSkill;
import prototype_skills.prototypeskills.Relationships.EmployeeOfBU;
import prototype_skills.prototypeskills.Relationships.CategoryBUSkill;


import java.util.*;

@Controller
public class BusinessUnitController {

    @Autowired
    BusinessUnitRepository businessUnitRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeOfBURepo employeeOfBURepo;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    CategoryBUSkillRepo categoryBUSkillRepo;

    @Autowired
    BUSkillRepo buSkillRepo;

    @PostMapping(path = "/addBusinessUnit")
    public String addBU(String buName, String location){

        BusinessUnit businessUnit = new BusinessUnit(buName, location);
        businessUnitRepository.save(businessUnit);

        return "index";
    }

//    @PostMapping(path = "/addEmployeeToBU")
//    public String addEMPBu(String employeeName, String buName, Model model){
//
//        Employee employee = employeeRepository.findByName(employeeName);
//        BusinessUnit bu = businessUnitRepository.findByName(buName);
//
//        EmployeeOfBU employeeOfBU = new EmployeeOfBU(employee, bu);
//
//        employeeOfBURepo.save(employeeOfBU);
//
//        model.addAttribute("buName", buName);
//
//        return "buPage";
//
//    }

    @GetMapping(path = "/getEmployeesOfBU")
    public String getEmployees(String buName, Model model) throws Exception{

        //BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        Collection<Employee> employees = employeeRepository.findAllByBU(buName);

        List<String> stringList = new ArrayList<>();

//        Map<String, String> stringList = new HashMap<>();
        employees.forEach(employee -> stringList.add(employee.getName()));

        model.addAttribute("buName", buName);
        model.addAttribute("employees", stringList);

        return "buPage";
    }

    @GetMapping(path = "/getBU")
    public String getBUs(Model model, String buName) throws Exception{

        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        String json = new ObjectMapper().writeValueAsString(businessUnit);

        model.addAttribute("buName", businessUnit.getName());
        model.addAttribute("buJsonObject", json);
        return "buPage";
    }

    @GetMapping(path = "/getAllBus")
    public String BUs(Model model){

        List<BusinessUnit> bus = businessUnitRepository.findAll();

        List<String> buss = new ArrayList<>();

        bus.forEach(businessUnit -> buss.add(businessUnit.getName()));
        model.addAttribute("buList", buss);
        return "buPage";
    }

    @PostMapping(path = "/addBUCategorySkills")
    public String addBUCategorySkills(Model model, String buName, String categorySkillName){
        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        CategorySkill categorySkill = new CategorySkill(categorySkillName);
        CategoryBUSkill categoryBUSkill = new CategoryBUSkill(businessUnit, categorySkill);

        categoryBUSkillRepo.save(categoryBUSkill);

        return "buPage";
    }

    //Adds a skill into the skill tree of a BU
    @PostMapping(path = "/addSkillsToBU")
    public String addProjectSkills(Model model, String buName, String skillName) {

//        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
//        Skill skill = skillRepository.findByName(skillName);
//        BUSkill buSkill = new BUSkill(businessUnit, skill);
//        buSkillRepo.save(buSkill);

        buSkillRepo.addSkillToBU(skillName, buName);

        model.addAttribute("buName", buName);

        return "buPage";
    }
    //Returns JSON formatted skill tree of BU
    @GetMapping(path = "/getBUSkillsTree")
    public String empSkill(Model model, String buName) throws Exception {
        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        List<HashMap<String, String>> queriedSkillTree = skillRepository.findBUSkillTree(businessUnit.getName());
        String skillTree = new ObjectMapper().writeValueAsString(queriedSkillTree);

        model.addAttribute("skillTree", skillTree);
        model.addAttribute("buName", businessUnit.getName());

        return "buPage";
    }


    //Probably won't need since the Skill Tree will return categories and skills
//    @GetMapping(path = "/getBUCategorySkillTree")
//    public String buSkillTree(Model model, String buName){
//
//        return "buPage";
//    }
}
