package cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}