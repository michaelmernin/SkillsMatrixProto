import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;
import prototype_skills.prototypeskills.DAO.ProjectRepository;
import prototype_skills.prototypeskills.DAO.Rels.SkillsNeededOnProjectRepo;
import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.Employee;
import prototype_skills.prototypeskills.Entities.Project;
import prototype_skills.prototypeskills.Entities.Skill;
import prototype_skills.prototypeskills.Relationships.SkillsNeededOnProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillsNeededOnProjectRepo skillsNeededOnProjectRepo;

    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping(path = "/project")
    public String projectInfo(){
        return "projectPage";
    }


    @PostMapping(path = "/addProject")
    public String addProject(String name, String location){

        Project project = new Project(name, location);
        projectRepository.save(project);

        return "projectPage";
    }

    @PostMapping(path = "/addProjectSkills")
    public String addProjectSkills(String projectName, String skillName, String essential) {

        Project project = projectRepository.findByName(projectName);
        Skill skill = skillRepository.findByName(skillName);
        //boolean isEssential = Boolean.valueOf(essential);
        SkillsNeededOnProject skillsNeededOnProject = new SkillsNeededOnProject(Boolean.valueOf(essential), project, skill);
        skillsNeededOnProjectRepo.save(skillsNeededOnProject);

        return "projectPage";
    }


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


}
