package prototype_skills.prototypeskills.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProgramController {


    @GetMapping(path = "/")
    public String welcome(){

        return "index";
    }


}