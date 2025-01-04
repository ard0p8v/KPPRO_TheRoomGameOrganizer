package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {
    List<Result> getAllResults();
    List<Result> getAllResultsByUser(User user);
    List<Result> getAllResultsByTournament(Tournament tournament);
    Result getResultById(long id);
    void deleteResultById(long id);
    void saveResult(Result result);
}
