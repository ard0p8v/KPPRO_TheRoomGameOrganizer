package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Registration;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.RegistrationRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.RegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public List<Registration> getRegistrationsByUser(User user) {
        return registrationRepository.findByUser(user);
    }

    @Override
    public Registration getRegistrationById(long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRegistrationById(long id) {
        Optional<Registration> registration = registrationRepository.findById(id);
        if (registration.isPresent()) {
            registrationRepository.delete(registration.get());
        }
    }

    @Override
    public void saveRegistration(Registration registration) {
        registrationRepository.save(registration);
    }
}
