package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Registration;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationService {
    List<Registration> getAllRegistrations();
    List<Registration> getRegistrationsByUser(User user);
    Registration getRegistrationById(long id);
    void deleteRegistrationById(long id);
    void saveRegistration(Registration registration);
}
