package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentType;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Game;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Registration;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.TournamentRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl.TournamentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    private Tournament createSampleTournament() {
        Game game = new Game();
        List<Registration> registrations = new ArrayList<>();
        List<Result> results = new ArrayList<>();

        return new Tournament(
                1L,
                "Tournament 1",
                new Date(),
                "The Room Pelhřimov",
                TournamentType.turnaj,
                TournamentStatus.PLÁNOVANÝ,
                10,
                game,
                results,
                registrations
        );
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTournaments() {
        Tournament tournament1 = createSampleTournament();
        Tournament tournament2 = createSampleTournament();
        tournament2.setId(2L);
        tournament2.setTitle("Tournament 2");

        when(tournamentRepository.findAll()).thenReturn(List.of(tournament1, tournament2));

        List<Tournament> tournaments = tournamentService.getAllTournaments();
        assertNotNull(tournaments);
        assertEquals(2, tournaments.size());
    }

    @Test
    public void testGetTournamentById() {
        Tournament tournament = createSampleTournament();

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));

        Tournament foundTournament = tournamentService.getTournamentById(1L);
        assertNotNull(foundTournament);
        assertEquals(tournament.getTitle(), foundTournament.getTitle());
    }

    @Test
    public void testSaveTournament() {
        Tournament tournament = createSampleTournament();
        tournament.setId(0L);  // ID should be 0 for new entities

        when(tournamentRepository.save(any(Tournament.class))).thenReturn(tournament);

        tournamentService.saveTournament(tournament);
        verify(tournamentRepository, times(1)).save(tournament);
    }

    @Test
    public void testDeleteTournamentById() {
        doNothing().when(tournamentRepository).deleteById(1L);

        tournamentService.deleteTournamentById(1L);
        verify(tournamentRepository, times(1)).deleteById(1L);
    }
}