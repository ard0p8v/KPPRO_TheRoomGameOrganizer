package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.ResultRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public List<Result> getAllResultsByUser(User user) {
        return resultRepository.findByUser(user);
    }

    @Override
    public List<Result> getAllResultsByTournament(Tournament tournament) {
        return resultRepository.findByTournament(tournament);
    }

    @Override
    public Result getResultById(long id) {
        return resultRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteResultById(long id) {
        Optional<Result> result = resultRepository.findById(id);
        result.ifPresent(value -> resultRepository.delete(value));
    }

    @Override
    public void saveResult(Result result) {
        resultRepository.save(result);
    }
}
