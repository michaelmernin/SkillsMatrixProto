package prototype_skills.prototypeskills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import prototype_skills.prototypeskills.DAO.*;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Project;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.HasSkill;

import java.util.*;

@Controller
public class ProgramController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BusinessUnitRepository businessUnitRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    HasSkillRepository hasSkillRepository;


    @GetMapping(path = "/")
    public String welcome(){

        return "index";
    }

//    @GetMapping(path = "/getBU")
//    public String getBUs(String text, Model model) throws Exception{
//        if (text.equals("all")) {
//            List<BusinessUnit> bus = businessUnitRepository.findALL();
//            bus.forEach(businessUnit -> model.addAttribute("BU Name", businessUnit.getName()));
//        }
//
//        BusinessUnit businessUnit = businessUnitRepository.findByName(text);
//        model.addAttribute("BU Name", businessUnit.getName());
//
//        return "buPage";
//    }


//    @GetMapping(path = "/getEmployeesOfBU")
//    public String getEmployees(String text, Model model) throws Exception{
//
//        BusinessUnit businessUnit = businessUnitRepository.findByName(text);
//        List<Employee> employees = employeeRepository.findAllByBusinessUnitWorkingFor(businessUnit);
//
//        model.addAttribute("BU Name", businessUnit.getName());
//        employees.forEach(employee -> model.addAttribute("employeeName", employee.getName()));
//
//        return "employeeOutput";
//    }

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

    @PostMapping(path = "/addEmployee")
    public String employeeRetrieval(String newName, String role, String location){

        Employee name = new Employee(newName, role, location);
        employeeRepository.save(name);

        return "index";
    }

    @PostMapping(path = "/addSkillToEmployee")
    public String addEmployeeSkill(String skillName, String empName, String expertise, String descrExpertise, Model model){

        Skill skill = skillRepository.findByName(skillName);
        Employee employee = employeeRepository.findFirstByName(empName);
        HasSkill rskill = new HasSkill(expertise, descrExpertise, employee, skill);

        hasSkillRepository.save(rskill);

        model.addAttribute("Employee", employee);
        model.addAttribute("employeeRole", employee.getRole());
        model.addAttribute("employeeName", employee.getName());
        //model.addAttribute("ProjectWorkingOn", employee.getProjectWorkingOn());
        //model.addAttribute("SKILLS_POSSESSED", employee.getSkillsPossessed());
        return "employeeOutput";
    }

    @PostMapping(path = "/addSkill")
    public String addSkill(String name, String resourceLinks, String description, String technology){

        Skill skill = new Skill(name, resourceLinks, description, technology);
        skillRepository.save(skill);

        return "addSkillsPage";
    }

    @GetMapping(path = "/skills")
    public String skills(Model model){

        return "addSkillsPage";
    }

    @GetMapping(path = "/employeeSkills")
    public String empSkill(Model model, String name){
        Employee employee = employeeRepository.findFirstByName(name);
        Collection<Skill> skillList = skillRepository.skillsList(employee.getName());

        List<String> stringList = new ArrayList<>();
        skillList.forEach(hasSkill -> stringList.add(hasSkill.getName()));
        model.addAttribute("skills", stringList);

        //skillList.forEach(hasSkill -> model.addAttribute("skills", hasSkill.getName()));

        return "testHasSkill";
    }

    @PostMapping(path = "/addProject")
    public void addProject(String name, String location){

        Project project = new Project(name, location);
        projectRepository.save(project);

    }

    @PostMapping(path = "/addBusinessUnit")
    public void addBU(String name, String location){

        BusinessUnit businessUnit = new BusinessUnit(name, location);
        businessUnitRepository.save(businessUnit);
    }


}
