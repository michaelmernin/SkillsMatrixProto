package prototype_skills.prototypeskills;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;
import prototype_skills.prototypeskills.DAO.Rels.EmployeeOfBURepo;
import prototype_skills.prototypeskills.DAO.Rels.FoundationalBUSkillRepo;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Project;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.EmployeeOfBU;
import prototype_skills.prototypeskills.Relationships.FoundationalBUSkill;
import prototype_skills.prototypeskills.Relationships.SkillsNeededOnProject;

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
    FoundationalBUSkillRepo foundationalBUSkillRepo;

    @PostMapping(path = "/addBusinessUnit")
    public String addBU(String buName, String location){

        BusinessUnit businessUnit = new BusinessUnit(buName, location);
        businessUnitRepository.save(businessUnit);

        return "index";
    }

    @PostMapping(path = "/addEmployeeToBU")
    public String addEMPBu(String employeeName, String buName, Model model){
        Employee employee = employeeRepository.findFirstByName(employeeName);
        BusinessUnit bu = businessUnitRepository.findByName(buName);

        EmployeeOfBU employeeOfBU = new EmployeeOfBU(employee, bu);

        employeeOfBURepo.save(employeeOfBU);

        model.addAttribute("buName", buName);

        return "buPage";
    }

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
    public String getBUs(String buName, Model model) throws Exception{

        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        model.addAttribute("buName", businessUnit.getName());

        return "buPage";
    }

    @GetMapping(path = "/BU")
    public String BUs(Model model){

        List<BusinessUnit> bus = businessUnitRepository.findAll();

        List<String> buss = new ArrayList<>();

        bus.forEach(businessUnit -> buss.add(businessUnit.getName()));
        model.addAttribute("buList", buss);
        return "buPage";
    }

    @PostMapping(path = "/addBUSkills")
    public String addProjectSkills(Model model, String buName, String skillName) {

        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        Skill skill = skillRepository.findByName(skillName);
        //boolean isEssential = Boolean.valueOf(essential);
        FoundationalBUSkill foundationalBUSkill = new FoundationalBUSkill(businessUnit, skill);
        foundationalBUSkillRepo.save(foundationalBUSkill);

        model.addAttribute("buName", buName);

        return "buPage";
    }

    @GetMapping(path = "/getBUSkills")
    public String empSkill(Model model, String buName){
        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        Collection<Skill> skillList = skillRepository.buSkillsList(businessUnit.getName());

        //List<Skill> skillList = skillRepository.findAllByEmployee(employee);

        List<String> stringList = new ArrayList<>();
        skillList.forEach(hasSkill -> stringList.add(hasSkill.getName()));
        model.addAttribute("skills", stringList);
        model.addAttribute("buName", buName);

        //skillList.forEach(hasSkill -> model.addAttribute("skills", hasSkill.getName()));

        return "buPage";
    }
}
