package cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
