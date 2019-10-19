package prototype_skills.prototypeskills.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.javafx.UnmodifiableArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;
import prototype_skills.prototypeskills.DAO.CategorySkillRepository;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;
import prototype_skills.prototypeskills.DAO.Rels.EmployeeOfBURepo;
import prototype_skills.prototypeskills.DAO.Rels.HasCategorySkillRepo;
import prototype_skills.prototypeskills.DAO.Rels.HasSkillRepository;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.CategorySkill;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.HasCategorySkill;
import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.lang.annotation.ElementType;

import java.util.*;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    HasSkillRepository hasSkillRepository;

    @Autowired
    CategorySkillRepository categorySkillRepository;

    @Autowired
    HasCategorySkillRepo hasCategorySkillRepo;

    @Autowired
    BusinessUnitRepository businessUnitRepository;

    @Autowired
    EmployeeOfBURepo employeeOfBURepo;

//    @GetMapping(path = "/employee")
//    public String employee(){
//
//        return "employeePage";
//    }

    @PostMapping(path = "/addEmployee")
    public String employeeRetrieval(String employeeName, String roleName, String locationName, String buName){

//        Employee name = new Employee(employeeName, roleName, location);
//        employeeRepository.save(name);

        BusinessUnit businessUnit = employeeOfBURepo.addEmployee(employeeName, locationName, roleName, buName);
        if(businessUnit == null){
            System.out.println("BU does not exist in system");
            //slf4j logger logback
        }

        return "index";
    }

    @PutMapping(path = "/editEmployee")
    //@PostMapping(path = "/editEmployee")
    public String employeeEdit(String newName, String editNewName, String editRole, String editLocation){

        List<Employee> queryList = employeeRepository.findByName(newName);
        Employee name = queryList.get(0);
        if(editLocation.length() != 0) {
            name.setLocation(editLocation);
        }
        if(editNewName.length() != 0) {
            name.setName(editNewName);
        }
        if(editRole.length() != 0) {
            name.setRole(editRole);
        }
        employeeRepository.save(name);

        return "employeePage";
    }

    @GetMapping(path = "/getEmployeeSkillExpertise")
    public String employeeSkillExpertise(Model model, String skillName, String employeeName){

        Map<String, String> r = hasSkillRepository.getHasSkillDetails(employeeName, skillName);

        model.addAttribute("currentJsonObject", r);
        model.addAttribute("employeeName", employeeName);

        return "employeePage";
    }

    @PostMapping(path = "/editEmployeeSkill")
    public String employeeEditSkill(Model model, String skillName, String expertise, String description, String employeeName) throws Exception{

        Map<String, String> r = hasSkillRepository.getHasSkillDetails(employeeName, skillName);
        String previousJsonObject = new ObjectMapper().writeValueAsString(r);
        String exp = r.get("expertise");
        String desc = r.get("expertiseDescription");

        model.addAttribute("buName", businessUnitRepository.findByEmployee(employeeName).getName());
        model.addAttribute("previousJsonObject", previousJsonObject);
        model.addAttribute("employeeName", employeeName);
        //model.addAttribute("newJsonObject", );

        return "employeeOutput";
    }

    @PostMapping(path = "/addSkillToEmployee")
    public String addEmployeeSkill(Model model, String skillName, String employeeName, String expertise, String expertiseDescription){

//        Skill skill = skillRepository.findByName(skillName);
//        Employee employee = employeeRepository.findByName(empName);
//        CategorySkill categorySkill = categorySkillRepository.findBySkill(skill.getName());
//        HasSkill rskill = new HasSkill(expertise, descrExpertise, employee, skill);
//        HasCategorySkill hasCategorySkill = new HasCategorySkill(employee, categorySkill);
//
//        hasCategorySkillRepo.save(hasCategorySkill);
//        hasSkillRepository.save(rskill);

        hasSkillRepository.addSkillToEmployee(skillName, expertise, expertiseDescription, employeeName);
        List<Employee> queryList = employeeRepository.findByName(employeeName);
        Employee employee = queryList.get(0);
        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(employeeName);

        //model.addAttribute("Employee", employee);
        model.addAttribute("employeeRole", employee.getRole());
        model.addAttribute("employeeName", employee.getName());
        model.addAttribute("buName", businessUnit.getName());

        return "employeeOutput";
    }

    @GetMapping(path = "/getEmployeeInfo")
    public String getEmployeeInfo(Model model, String employeeName) throws Exception{

        //Optional<Employee> em = employeeRepository.findById(Long.parseLong("5"));

        List<Employee> employeeList = employeeRepository.findByName(employeeName);
        if(employeeList.size() > 1){
            return "mulitple users found with same name, give them list to choose which employee they are";
        } else {
            Employee employee = employeeList.get(0);
        }

        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(employeeName);

        String employeeJsonObject = new ObjectMapper().writeValueAsString(employeeList.get(0));

        model.addAttribute("employeeJsonObject", employeeJsonObject);
        model.addAttribute("buName", businessUnit.getName());
        model.addAttribute("employeeRole", employeeList.get(0).getRole());
        model.addAttribute("employeeName", employeeName);


        return "employeeOutput";
    }

    @GetMapping(path = "/employeeSkills")
    public String empSkill(Model model, String employeeName){

        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(employeeName);
        Collection<Skill> skillList = skillRepository.skillsList(employeeName);

        List<String> stringList = new ArrayList<>();
        skillList.forEach(hasSkill -> stringList.add(hasSkill.getName()));

        model.addAttribute("buName", businessUnit.getName());
        model.addAttribute("employeeName", employeeName);
        model.addAttribute("skills", stringList);

        return "testHasSkill";
    }
}
