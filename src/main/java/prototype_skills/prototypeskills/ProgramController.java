package prototype_skills.prototypeskills;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prototype_skills.prototypeskills.Entities.Project;

@Controller
public class ProgramController {


    @GetMapping(path = "/")
    public String welcome(){

        return "index";
    }


}