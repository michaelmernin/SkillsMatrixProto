package prototype_skills.prototypeskills;

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


}
