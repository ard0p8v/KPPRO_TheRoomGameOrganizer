package cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUser(User user);
    List<Result> findByTournament(Tournament tournament);
}
