package prototype_skills.prototypeskills.Controller;

import com.sun.javafx.UnmodifiableArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;
import prototype_skills.prototypeskills.DAO.Rels.HasSkillRepository;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Skill;
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

    @GetMapping(path = "/employee")
    public String employee(){

        return "employeePage";
    }

    @PostMapping(path = "/addEmployee")
    public String employeeRetrieval(String newName, String role, String location){

        Employee name = new Employee(newName, role, location);
        employeeRepository.save(name);

        return "employeePage";
    }

    @PostMapping(path = "/editEmployee")
    public String employeeEdit(String newName, String editNewName, String editRole, String editLocation){

        Employee name = employeeRepository.findFirstByName(newName);
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

    @PostMapping(path = "/editEmployeeSkill")
    public String employeeEditSkill(String skillName, String expertise, String description, String name){

        //HasSkill hasSkill = hasSkillRepository.editHasSkill(name, skillName, expertise);

        Map<String, String> r = hasSkillRepository.getHasSkill(name, skillName);

        String exp = r.get("expertise");
        String desc = r.get("descriptionOfExpertise");

        int x = 5;


//        HasSkill hasSkill = hasSkillRepository.getHasSkill(name, skillName);
//        HasSkill r = skillRepository.getSkill(name, skillName);
//        if(expertise.length() != 0) {
//            hasSkill.setExpertise(expertise);
//        } else {
//            hasSkill.setExpertise("l");
//        }
//        if(description.length() != 0) {
//            hasSkill.setDescriptionOfExpertise(description);
//        } else {
//            hasSkill.setDescriptionOfExpertise("s");
//        }
//
//
//
//        hasSkillRepository.save(hasSkill);

        return "employeePage";
    }

    @PostMapping(path = "/addSkillToEmployee")
    public String addEmployeeSkill(String skillName, String empName, String expertise, String descrExpertise, Model model){

        Skill skill = skillRepository.findByName(skillName);
        Employee employee = employeeRepository.findFirstByName(empName);
        HasSkill rskill = new HasSkill(expertise, descrExpertise, employee, skill);
//        rskill.setExpertise(expertise);
//        rskill.setDescriptionOfExpertise(descrExpertise);
//        rskill.setEmployee(employee);
//        rskill.setSkill(skill);

        hasSkillRepository.save(rskill);

        model.addAttribute("Employee", employee);
        model.addAttribute("employeeRole", employee.getRole());
        model.addAttribute("employeeName", employee.getName());
        //model.addAttribute("ProjectWorkingOn", employee.getProjectWorkingOn());
        //model.addAttribute("SKILLS_POSSESSED", employee.getSkillsPossessed());
        return "employeeOutput";
    }

    @GetMapping(path = "/getEmployeeInfo")
    public String getEmployeeInfo(String text, Model model) throws Exception{

        Employee employee = employeeRepository.findFirstByName(text);

        model.addAttribute("Employee", employee);
        model.addAttribute("employeeRole", employee.getRole());
        model.addAttribute("employeeName", employee.getName());
        //model.addAttribute("ProjectWorkingOn", employee.getProjectWorkingOn());
        //model.addAttribute("SKILLS_POSSESSED", employee.getSkillsPossessed());
        //employee.getSkillsPossessed().forEach(skill -> model.addAttribute("SKILLS_POSSESSED", skill.getName()));


        return "employeeOutput";
    }

    @GetMapping(path = "/employeeSkills")
    public String empSkill(Model model, String name){
        Employee employee = employeeRepository.findFirstByName(name);
        Collection<Skill> skillList = skillRepository.skillsList(employee.getName());

        //List<Skill> skillList = skillRepository.findAllByEmployee(employee);

        List<String> stringList = new ArrayList<>();
        skillList.forEach(hasSkill -> stringList.add(hasSkill.getName()));
        model.addAttribute("skills", stringList);

        //skillList.forEach(hasSkill -> model.addAttribute("skills", hasSkill.getName()));

        return "testHasSkill";
    }
}
