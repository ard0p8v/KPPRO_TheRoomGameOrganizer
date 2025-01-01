package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
    List<Game> getAllGames();
    Game getGameById(long id);
    void deleteGameById(long id);
    void saveGame(Game game);
}
