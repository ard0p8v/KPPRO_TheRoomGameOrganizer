package cz.uhk.fim.kppro.kppro_theroomgameorganizer.controller;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.model.User;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.security.MyUserDetails;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/",})
    public String getListUser(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "user_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        if(user != null) {
            model.addAttribute("user", user);
            return "user_detail";
        }
        return "redirect:/users/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        User logUser = userService.getUserById(id);

        if (logUser.getRole().equals("ADMIN") || user.getId() == id) {
            redirectAttributes.addFlashAttribute("isFailDeleteUser", true);
            return "redirect:/users/";
        }

        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("isSuccessDeleteUser", true);
        return "redirect:/users/";
    }

}
