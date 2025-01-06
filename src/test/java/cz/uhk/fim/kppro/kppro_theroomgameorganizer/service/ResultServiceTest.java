package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Result;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.Tournament;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.ResultRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl.ResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultServiceImpl resultService;

    private Result result;
    private User user;
    private Tournament tournament;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);

        tournament = new Tournament();
        tournament.setId(1L);

        result = new Result();
        result.setId(1L);
        result.setUser(user);
        result.setTournament(tournament);
        result.setPosition(1);
        result.setScore(100);
    }

    @Test
    public void testGetAllResults() {
        List<Result> results = Arrays.asList(result);
        when(resultRepository.findAll()).thenReturn(results);

        List<Result> fetchedResults = resultService.getAllResults();
        assertEquals(1, fetchedResults.size());
        verify(resultRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllResultsByUser() {
        List<Result> results = Arrays.asList(result);
        when(resultRepository.findByUser(user)).thenReturn(results);

        List<Result> fetchedResults = resultService.getAllResultsByUser(user);
        assertEquals(1, fetchedResults.size());
        verify(resultRepository, times(1)).findByUser(user);
    }

    @Test
    public void testGetAllResultsByTournament() {
        List<Result> results = Arrays.asList(result);
        when(resultRepository.findByTournament(tournament)).thenReturn(results);

        List<Result> fetchedResults = resultService.getAllResultsByTournament(tournament);
        assertEquals(1, fetchedResults.size());
        verify(resultRepository, times(1)).findByTournament(tournament);
    }

    @Test
    public void testGetResultById() {
        when(resultRepository.findById(1L)).thenReturn(Optional.of(result));

        Result fetchedResult = resultService.getResultById(1L);
        assertNotNull(fetchedResult);
        assertEquals(1L, fetchedResult.getId());
        verify(resultRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteResultById() {
        doNothing().when(resultRepository).deleteById(1L);

        resultService.deleteResultById(1L);
        verify(resultRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSaveResult() {
        when(resultRepository.save(result)).thenReturn(result);

        resultService.saveResult(result);
        verify(resultRepository, times(1)).save(result);
    }
}
