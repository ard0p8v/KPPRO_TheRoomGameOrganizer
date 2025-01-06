package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.RegistrationStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Registration;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.RegistrationRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    private Registration registration;
    private User user;
    private Tournament tournament;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);

        tournament = new Tournament();
        tournament.setId(1L);

        registration = new Registration();
        registration.setId(1L);
        registration.setDate(new Date());
        registration.setStatus(RegistrationStatus.ČEKAJÍCÍ);
        registration.setNote("Poznamka");
        registration.setUser(user);
        registration.setTournament(tournament);
    }

    @Test
    public void testGetAllRegistrations() {
        List<Registration> registrations = Arrays.asList(registration);
        when(registrationRepository.findAll()).thenReturn(registrations);

        List<Registration> fetchedRegistrations = registrationService.getAllRegistrations();
        assertEquals(1, fetchedRegistrations.size());
        verify(registrationRepository, times(1)).findAll();
    }

    @Test
    public void testGetRegistrationsByUser() {
        List<Registration> registrations = Arrays.asList(registration);
        when(registrationRepository.findByUser(user)).thenReturn(registrations);

        List<Registration> fetchedRegistrations = registrationService.getRegistrationsByUser(user);
        assertEquals(1, fetchedRegistrations.size());
        verify(registrationRepository, times(1)).findByUser(user);
    }

    @Test
    public void testGetRegistrationById() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.of(registration));

        Registration fetchedRegistration = registrationService.getRegistrationById(1L);
        assertNotNull(fetchedRegistration);
        assertEquals(1L, fetchedRegistration.getId());
        verify(registrationRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteRegistrationById() {
        doNothing().when(registrationRepository).deleteById(1L);

        registrationService.deleteRegistrationById(1L);
        verify(registrationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSaveRegistration() {
        when(registrationRepository.save(registration)).thenReturn(registration);

        registrationService.saveRegistration(registration);
        verify(registrationRepository, times(1)).save(registration);
    }
}
