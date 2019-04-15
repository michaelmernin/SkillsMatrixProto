package prototype_skills.prototypeskills.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prototype_skills.prototypeskills.DAO.BusinessUnitRepository;
import prototype_skills.prototypeskills.DAO.EmployeeRepository;



import prototype_skills.prototypeskills.DAO.SkillRepository;
import prototype_skills.prototypeskills.Entities.BusinessUnit;
import prototype_skills.prototypeskills.Entities.Employee;

import prototype_skills.prototypeskills.Entities.Skill;

import java.util.*;

@Controller
//@RestController
public class AdvancedQueryController {

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    SkillRepository skillRepository;

    @Autowired
    BusinessUnitRepository businessUnitRepository;

    @Value("${client.pseudo.property}")
    private String pseudoProperty;

    @GetMapping("/property")
    public ResponseEntity<String> getProperty() {
        return ResponseEntity.ok(pseudoProperty);
    }



    @GetMapping(path = "/compareEmpToBUSkills")
    public String empBUSkill(Model model, String employeeName, String buName) throws Exception {
//        Employee employee = employeeRepository.findFirstByName(employeeName);
//        BusinessUnit businessUnit = businessUnitRepository.findByName(buName);

//        List<String> noSkills = new ArrayList<>();
//        List<String> hasSkills = new ArrayList<>();
        //skillList.forEach(skill -> hasSkills.add(skill.getName()));
        //noSkillList.forEach(nonSkill -> noSkills.add(nonSkill.getName()));
        //model.addAttribute("Employee", employee);
//        Map<String, String> skillList = skillRepository.employeeHasBUSkills(employeeName, buName);
        //List<String> skillList = skillRepository.employeeHasBUSkills(employeeName, buName);

        //skillList returns {Skill= Spring Framework, Expertise=3}
        //Map<String, String> skillList = skillRepository.employeeHasBUSkills(employeeName, buName);

        //Using Jackson JSON
//        Map<String, String> skillQuery = skillRepository.employeeHasBUSkills(employeeName, buName);
//        //skillList is a JSON object {"Skill":"Spring Framework", "Expertise":"3"}  ("Skill" and "Expertise" key names can be arbitrarily described)
//        String skillList = new ObjectMapper().writeValueAsString(skillQuery);

//       List<com.fasterxml.jackson.databind.node.ObjectNode> skillList = skillRepository.employeeHasBUSkills(buName);
        List<HashMap<String, String>> queri = skillRepository.employeeHasBUSkills(buName);
        String skillList = new ObjectMapper().writeValueAsString(queri);
//        Output for each skill:
//        {
//            "value": {
//            "_type": "Skill",
//                    "name": "UX/UI Design",
//                    "_id": 480,
//                    "skill_of_category": [{
//                "_type": "Category",
//                        "name": "Creative",
//                        "_id": 481,
//                        "category_bu_skill": [{
//                    "_type": "BusinessUnit",
//                            "name": "Minneapolis",
//                            "location": "Minneapolis, MN",
//                            "_id": 528
//                }]
//            }]
//        }
//        }

        //Map<String, String> skillList = skillRepository.employeeHasBUSkills(employeeName, buName);

        //!!Working Version
        //returns a list of names,
       //List<String> noSkillList = skillRepository.employeeNeedsBUSkills(employeeName, buName);

        List<String> queries = skillRepository.employeeNeedsBUSkills(employeeName, buName);
        Map<String, List<String>> middle = new HashMap<>();
        middle.put("Skill", queries);
        String noSkillList = new ObjectMapper().writeValueAsString(middle);
        // outputs: {"Skill":["System Firewalls","Network Firewalls","Linux Shell Scripting"]}
        //String noSkillList = new ObjectMapper().writeValueAsString(queries);

        //Map<String, String> noSkillList = skillRepository.buSkillTree(employeeName, buName);

        //Map<String, String> noList = skillRepository.employeeNeedsBUSkills(employeeName, buName);
        //String noSkillList = new ObjectMapper().writeValueAsString(noList);





        model.addAttribute("employeeName", employeeName);
        model.addAttribute("buName", buName);
        model.addAttribute("skills", skillList);
        model.addAttribute("nonSkills", noSkillList);


        return "comparePage";
    }
}
