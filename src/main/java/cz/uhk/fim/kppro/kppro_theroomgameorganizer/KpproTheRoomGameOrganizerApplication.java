package cz.uhk.fim.kppro.kppro_theroomgameorganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KpproTheRoomGameOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KpproTheRoomGameOrganizerApplication.class, args);
    }

    /*private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public KpproTheRoomGameOrganizerApplication(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            addUser(50L, "pavel", "ardolf", "ardolf@koncepthk.cz", "heslo", "ADMIN");
            addUser(51L, "jan", "novak", "novak@koncepthk.cz", "heslo", "USER");
        };
    }

    private void addUser(Long id, String name, String surname, String email, String password, String role) {
        if (userService.findUserByEmail(email) == null) {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userService.saveUser(user);
        }
    }*/
}
