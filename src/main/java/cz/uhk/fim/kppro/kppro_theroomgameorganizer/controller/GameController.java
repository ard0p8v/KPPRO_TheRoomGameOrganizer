package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;


import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/games")
    public String getListGames(Model model) {
        List<Game> games = gameService.getAllGames();

        model.addAttribute("games", gameService.getAllGames());
        return "game_list";
    }
}
