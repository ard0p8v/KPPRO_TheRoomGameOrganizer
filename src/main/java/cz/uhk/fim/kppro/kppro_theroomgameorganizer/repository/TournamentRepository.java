package cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
