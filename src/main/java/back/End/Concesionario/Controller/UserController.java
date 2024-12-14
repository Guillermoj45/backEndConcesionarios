package back.End.Concesionario.Controller;

import back.End.Concesionario.DTO.LoginDTO;
import back.End.Concesionario.DTO.RegisterDTO;
import back.End.Concesionario.Model.User;
import back.End.Concesionario.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping
    public String addUser(@RequestBody RegisterDTO user) {
        return userService.register(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
