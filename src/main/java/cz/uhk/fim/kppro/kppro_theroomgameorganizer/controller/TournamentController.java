package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentType;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.GameService;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {
    private TournamentService tournamentService;
    private GameService gameService;

    @Autowired
    public TournamentController(TournamentService tournamentService, GameService gameService) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String getListTournament(Model model) {
        List<Tournament> tourmanents = tournamentService.getAllTournaments();

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
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
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
}
