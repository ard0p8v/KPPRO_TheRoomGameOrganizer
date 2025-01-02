package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @Bean
    CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("ardolf@koncepthk.cz") == null) {
                User user = new User();
                user.setName("Admin");
                user.setSurname("User");
                user.setEmail("admin@example.com");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRole("ROLE_ADMIN");
                userRepository.save(user);
            }
        };
    }
}
