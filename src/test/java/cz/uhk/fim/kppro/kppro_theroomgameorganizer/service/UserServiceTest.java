package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.UserRepository;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.security.MyUserDetails;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("test");
        user.setSurname("test");
        user.setUsername("test");
        user.setEmail("test@test.cz");
        user.setPassword("heslo");
        user.setRole("USER");
    }

    @Test
    public void testGetUsers() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> fetchedUsers = userService.getUsers();
        assertEquals(1, fetchedUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail("test@test.cz")).thenReturn(user);

        User fetchedUser = userService.findByEmail("test@test.cz");
        assertNotNull(fetchedUser);
        assertEquals("test@test.cz", fetchedUser.getEmail());
        verify(userRepository, times(1)).findByEmail("test@test.cz");
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User fetchedUser = userService.getUserById(1L);
        assertNotNull(fetchedUser);
        assertEquals(1L, fetchedUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteUserById() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(user)).thenReturn(user);

        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testLoadUserByUsername() {
        when(userRepository.findByEmail("test@test.cz")).thenReturn(user);

        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername("test@test.cz");
        assertNotNull(userDetails);
        assertEquals("test@test.cz", userDetails.getUsername());
        verify(userRepository, times(1)).findByEmail("test@test.cz");
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(userRepository.findByEmail("test")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("test");
        });
        verify(userRepository, times(1)).findByEmail("test");
    }
}