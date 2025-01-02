package cz.uhk.fim.kppro.kppro_theroomgameorganizer.service;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
