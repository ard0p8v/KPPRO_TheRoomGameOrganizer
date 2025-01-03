package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String theRoom() {
        return "redirect:/tournaments/";
    }

    @GetMapping("/403")
    public String forbidden(){
        return "403";
    }
}
