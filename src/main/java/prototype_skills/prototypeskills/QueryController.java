package prototype_skills.prototypeskills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;
import prototype_skills.prototypeskills.DAO.ProjectRepository;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Project;
import prototype_skills.prototypeskills.Entities.Skill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class QueryController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    BusinessUnitRepository businessUnitRepository;


    @GetMapping(path = "/compareEmpToSkills")
    public String empSkill(Model model, String employeeName, String projectName){
        Employee employee = employeeRepository.findFirstByName(employeeName);
        Project project = projectRepository.findByName(projectName);
        Collection<Skill> skillList = skillRepository.employeeHasProjectSkills(employee.getName(), project.getName());
        Collection<Skill> noSkillList = skillRepository.employeeNeedsProjectSkills(employee.getName(), project.getName());
        List<String> noSkills = new ArrayList<>();
        List<String> hasSkills = new ArrayList<>();
        skillList.forEach(skill -> hasSkills.add(skill.getName()));
        noSkillList.forEach(nonSkill -> noSkills.add(nonSkill.getName()));
        model.addAttribute("Employee", employee);
        model.addAttribute("employeeName", employee.getName());
        model.addAttribute("projectName", project.getName());
        model.addAttribute("skills", hasSkills);
        model.addAttribute("nonSkills", noSkills);

        //skillList.forEach(hasSkill -> model.addAttribute("skills", hasSkill.getName()));

        return "comparePage";
    }

    @GetMapping(path = "/compareEmpToBUSkills")
    public String empBUSkill(Model model, String employeeName, String buName){
        Employee employee = employeeRepository.findFirstByName(employeeName);
        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);
        Collection<Skill> skillList = skillRepository.employeeHasBUSkills(employee.getName(), businessUnit.getName());
        Collection<Skill> noSkillList = skillRepository.employeeNeedsBUSkills(employee.getName(), businessUnit.getName());
        List<String> noSkills = new ArrayList<>();
        List<String> hasSkills = new ArrayList<>();
        skillList.forEach(skill -> hasSkills.add(skill.getName()));
        noSkillList.forEach(nonSkill -> noSkills.add(nonSkill.getName()));
        model.addAttribute("Employee", employee);
        model.addAttribute("employeeName", employee.getName());
        model.addAttribute("projectName", businessUnit.getName());
        model.addAttribute("skills", hasSkills);
        model.addAttribute("nonSkills", noSkills);

        //skillList.forEach(hasSkill -> model.addAttribute("skills", hasSkill.getName()));

        return "comparePage";
    }
}
