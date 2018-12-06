package prototype_skills.prototypeskills.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;

import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;

import prototype_skills.prototypeskills.Entities.Skill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class AdvancedQueryController {

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    SkillRepository skillRepository;

    @Autowired
    BusinessUnitRepository businessUnitRepository;



    @GetMapping(path = "/compareEmpToBUSkills")
    public String empBUSkill(Model model, String employeeName, String buName){
//        Employee employee = employeeRepository.findFirstByName(employeeName);
//        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);

//        List<String> noSkills = new ArrayList<>();
//        List<String> hasSkills = new ArrayList<>();
        //skillList.forEach(skill -> hasSkills.add(skill.getName()));
        //noSkillList.forEach(nonSkill -> noSkills.add(nonSkill.getName()));
        //model.addAttribute("Employee", employee);
        List<String> skillList = skillRepository.employeeHasBUSkills(employeeName, buName);
        List<String> noSkillList = skillRepository.employeeNeedsBUSkills(employeeName, buName);
        model.addAttribute("employeeName", employeeName);
        model.addAttribute("buName", buName);
        model.addAttribute("skills", skillList);
        model.addAttribute("nonSkills", noSkillList);


        return "comparePage";
    }
}
