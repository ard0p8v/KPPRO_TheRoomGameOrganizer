package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/theRoom"})
    public String theRoom() { return "index"; }
}
