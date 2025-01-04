package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;


import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/games/")
    public String getListGames(Model model) {
        List<Game> games = gameService.getAllGames();

        model.addAttribute("games", gameService.getAllGames());
        return "game_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Game game = gameService.getGameById(id);
        if(game != null) {
            model.addAttribute("game", game);
            return "game_detail";
        }
        return "redirect:/tournaments/";
    }
}
