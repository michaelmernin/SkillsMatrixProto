package prototype_skills.prototypeskills.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.UnmodifiableArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

import static java.lang.Long.valueOf;

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


    @PostMapping(path = "/addEmployee")
    public String employeeRetrieval(String employeeName, String roleName, String locationName, String buName){

        BusinessUnit businessUnit = employeeOfBURepo.addEmployee(employeeName, locationName, roleName, buName);
        if(businessUnit == null){
            System.out.println("BU does not exist in system");
            //slf4j logger logback
        }

        return "index";
    }

    @PostMapping(path = "/editEmployee")
    public String employeeEdit(String newName, String editNewName, String editRole, String editLocation){

        Employee name = employeeRepository.findByName(newName);
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
        Employee employee = employeeRepository.findByName(employeeName);
        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(employeeName);

        model.addAttribute("employeeRole", employee.getRole());
        model.addAttribute("employeeName", employee.getName());
        model.addAttribute("buName", businessUnit.getName());

        return "employeeOutput";
    }

    @GetMapping(path = "/getEmployeeInfo")
    public String getEmployeeInfo(Model model, String employeeName) throws Exception{

        List<Employee> queriedList = employeeRepository.findAllByName(employeeName);
        if(queriedList.size() > 1){
            String employeeList = new ObjectMapper().writeValueAsString(queriedList);
            model.addAttribute("error", "Multiple Users With Name exist");
            model.addAttribute("employeeList", employeeList);
            return "selectUser";
        } else {
            Employee employee = queriedList.get(0);
            String employeeJsonObject = new ObjectMapper().writeValueAsString(employee);
            model.addAttribute("employeeRole", employee.getRole());
            model.addAttribute("employeeName", employee.getName());
            model.addAttribute("employeeJsonObject", employeeJsonObject);
        }

        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(employeeName);
        model.addAttribute("buName", businessUnit.getName());

        return "employeeOutput";
    }

    @GetMapping(path = "/getEmployeeInfoById")
    public String getEmployeeInfoById(Model model, String employeeId) throws Exception{

        Optional<Employee> queryEmployee = employeeRepository.findById(Long.valueOf(employeeId));

        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(queryEmployee.get().getName());
        model.addAttribute("buName", businessUnit.getName());
        model.addAttribute("employeeName", queryEmployee.get().getName());

        return "employeeOutput";
    }

    @GetMapping(path = "/getEmployeeSkillTree")
    public String employeeSkillTree(Model model, String employeeName) throws Exception {

        List<HashMap<String, String>> queriedTree = hasSkillRepository.getEmployeeSkillTree(employeeName);
        String skillTree = new ObjectMapper().writeValueAsString(queriedTree);
        BusinessUnit businessUnit = businessUnitRepository.findByEmployee(employeeName);

        model.addAttribute("skillTree", skillTree);
        model.addAttribute("employeeName", employeeName);
        model.addAttribute("buName", businessUnit.getName());

        return "employeeOutput";
    }
}
