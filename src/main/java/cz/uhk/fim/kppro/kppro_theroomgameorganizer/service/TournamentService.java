package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TournamentService {
    List<Tournament> getAllTournaments();
    Tournament getTournamentById(long id);
    void deleteTournamentById(long id);
    void saveTournament(Tournament tournament);
}
