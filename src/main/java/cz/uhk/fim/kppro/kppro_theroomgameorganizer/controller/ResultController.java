package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.RegistrationService;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.ResultService;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.TournamentService;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;
    private final TournamentService tournamentService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public ResultController(ResultService resultService, TournamentService tournamentService, UserService userService, RegistrationService registrationService) {
        this.resultService = resultService;
        this.tournamentService = tournamentService;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/{tournament_id}/delete/{id}")
    public String delete(@PathVariable long id, @PathVariable long tournament_id, RedirectAttributes redirectAttributes){
        resultService.deleteResultById(id);
        redirectAttributes.addFlashAttribute("isSuccessDelete", true);
        return "redirect:/tournaments/" + tournament_id + "/results";
    }

    @GetMapping("/{tournament_id}/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Result result = resultService.getResultById(id);
        Tournament tournament = tournamentService.getTournamentById(result.getTournament().getId());
        List<Result> results = resultService.getAllResultsByTournament(tournament);

        if (result != null) {
            model.addAttribute("editResult", result);
            model.addAttribute("tournament", tournament);
            model.addAttribute("results", results);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("editNeeded", true);
            return "result_edit_list";
        }
        return "redirect:/tournaments/";
    }

    @GetMapping("/{tournament_id}/create")
    public String create(Model model, @PathVariable long tournament_id) {
        Tournament tournament = tournamentService.getTournamentById(tournament_id);
        List<Result> results = resultService.getAllResultsByTournament(tournament);

        model.addAttribute("editResult", new Result());
        model.addAttribute("tournament", tournament);
        model.addAttribute("results", results);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("editNeeded", true);
        model.addAttribute("edit", false);

        return "result_edit_list";
    }

    @PostMapping("/save")
    public String save(@Valid Result result, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            Tournament tournament = tournamentService.getTournamentById(result.getTournament().getId());
            List<Result> results = resultService.getAllResultsByTournament(tournament);

            model.addAttribute("editResult", result);
            model.addAttribute("tournament", tournament);
            model.addAttribute("results", results);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("editNeeded", true);
            return "result_edit_list";
        }

        resultService.saveResult(result);

        Tournament tournament = tournamentService.getTournamentById(result.getTournament().getId());
        List<Result> results = resultService.getAllResultsByTournament(tournament);

        model.addAttribute("tournament", tournament);
        model.addAttribute("results", results);
        model.addAttribute("isSuccess", true);

        return "result_edit_list";
    }
}
