package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.RegistrationStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentType;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Registration;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.security.MyUserDetails;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.RegistrationService;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private RegistrationService registrationService;
    private UserService userService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getListUserRegistrations(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());

        List<Registration> registrations = registrationService.getRegistrationsByUser(user);
        model.addAttribute("registrations", registrations);
        return "registration_list";
    }

    @GetMapping("/cancel/{id}")
    public String edit(Model model, @PathVariable long id) {
        Registration registration = registrationService.getRegistrationById(id);
        if(registration != null) {
            registration.setStatus(RegistrationStatus.ZRUŠENO);

            registrationService.saveRegistration(registration);
            return "redirect:/registrations/?canceled=true";
        }
        return "redirect:/registrations/";
    }

}
