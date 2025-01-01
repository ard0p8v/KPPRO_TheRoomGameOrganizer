package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.GameRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        /*Game game = new Game();
        game.setTitle("Test Game");
        game.setMaxPlayers(2);
        gameRepository.save(game);*/

        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGameById(long id) {
        Optional<Game> game = gameRepository.findById(id);
        game.ifPresent(value -> gameRepository.delete(value));
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }
}
