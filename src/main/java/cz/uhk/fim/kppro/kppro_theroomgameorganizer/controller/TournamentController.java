package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.RegistrationStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentType;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Registration;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.security.MyUserDetails;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {
    private final UserService userService;
    private final TournamentService tournamentService;
    private final GameService gameService;
    private final RegistrationService registrationService;
    private final ResultService resultService;

    @Autowired
    public TournamentController(TournamentService tournamentService, GameService gameService, RegistrationService registrationService, UserService userService, ResultService resultService) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
        this.registrationService = registrationService;
        this.userService = userService;
        this.resultService = resultService;
    }

    @GetMapping({"/", "/login", "/logout"})
    public String getListTournament(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            User user = userService.getUserById(userDetails.getId());

            if (user != null) {
                model.addAttribute("welcome", user.getName() + " " + user.getSurname());
            }
        }

        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "tournament_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if(tournament != null) {
            model.addAttribute("tournament", tournament);
            return "tournament_detail";
        }
        return "redirect:/tournaments/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if(tournament != null) {
            model.addAttribute("tournament", tournament);
            model.addAttribute("games", gameService.getAllGames());
            model.addAttribute("types", TournamentType.values());
            model.addAttribute("statuses", TournamentStatus.values());
            model.addAttribute("edit", true);
            return "tournament_edit";
        }
        return "redirect:/tournaments/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("tournament", new Tournament());
        model.addAttribute("games", gameService.getAllGames());
        model.addAttribute("types", TournamentType.values());
        model.addAttribute("statuses", TournamentStatus.values());
        model.addAttribute("edit", false);
        return "tournament_edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes){
        tournamentService.deleteTournamentById(id);
        redirectAttributes.addFlashAttribute("isSuccessDelete", true);
        return "redirect:/tournaments/";
    }

    @PostMapping("/save")
    public String save(@Valid Tournament tournament, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("tournament", tournament);
            model.addAttribute("games", gameService.getAllGames());
            model.addAttribute("types", TournamentType.values());
            model.addAttribute("statuses", TournamentStatus.values());
            model.addAttribute("edit", true);
            return "tournament_edit";
        }

        tournamentService.saveTournament(tournament);
        Tournament tournament_detail = tournamentService.getTournamentById(tournament.getId());
        if (tournament_detail != null) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("tournament", tournament_detail);
            return "tournament_detail";
        }
        return "redirect:/tournaments/";
    }

    @GetMapping("/{id}/register")
    public String showRegistrationForm(@PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(id);
        model.addAttribute("tournament", tournament);
        return "registration_edit";
    }

    @PostMapping("/{id}/register")
    public String registerForTournament(@PathVariable Long id, @RequestParam("userId") Long userId, @RequestParam("note") String note, RedirectAttributes redirectAttributes) {
        Tournament tournament = tournamentService.getTournamentById(id);
        User user = userService.getUserById(userId);

        if (tournament.getStatus().equals(TournamentStatus.UKONČENÝ)) {
            redirectAttributes.addFlashAttribute("isFailRegistrationEnded", true);
            return "redirect:/registrations/";
        }

        if (tournament.getFreePlaces() == 0) {
            redirectAttributes.addFlashAttribute("isFailRegistrationZero", true);
            return "redirect:/registrations/";
        }

        List<Registration> userReservations = registrationService.getRegistrationsByUser(user);
        boolean alreadyRegistered = false;
        if (!userReservations.isEmpty()) {
            for (Registration registration : userReservations) {
                if (registration.getTournament().getId() == tournament.getId()) {
                    if (!registration.getStatus().equals(RegistrationStatus.ZRUŠENO)) {
                        alreadyRegistered = true;
                        break;
                    }
                }
            }
        }

        if (alreadyRegistered) {
            redirectAttributes.addFlashAttribute("isFailRegistration", true);
            return "redirect:/registrations/";
        }

        if (tournament.getFreePlaces() > 0) {
            Registration registration = new Registration();
            registration.setDate(new Date());
            registration.setStatus(RegistrationStatus.ČEKAJÍCÍ);
            registration.setNote(note);
            registration.setUser(user);
            registration.setTournament(tournament);

            registrationService.saveRegistration(registration);

            tournament.setFreePlaces(tournament.getFreePlaces() - 1);
            tournamentService.saveTournament(tournament);
        }

        return "redirect:/tournaments/?registration=true";
    }

    @GetMapping("/{id}/results")
    public String getListResultsForm(@PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(id);
        List<Result> results = resultService.getAllResultsByTournament(tournament);

        model.addAttribute("tournament", tournament);
        model.addAttribute("results", results);
        return "result_edit_list";
    }

    @GetMapping("/end/{id}")
    public String end(Model model, @PathVariable long id) {
        Tournament tournament = tournamentService.getTournamentById(id);

        if (tournament != null) {
            tournament.setStatus(TournamentStatus.UKONČENÝ);

            tournamentService.saveTournament(tournament);
            return "redirect:/tournaments/?ended=true";
        }
        return "redirect:/tournaments/";
    }
}
