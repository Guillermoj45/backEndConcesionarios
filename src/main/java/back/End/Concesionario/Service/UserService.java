package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.LoginDTO;
import back.End.Concesionario.DTO.RegisterDTO;
import back.End.Concesionario.DTO.UserNameAndAdminDTO;
import back.End.Concesionario.Model.Enum.Rol;
import back.End.Concesionario.Model.User;
import back.End.Concesionario.Repository.UserRepository;
import back.End.Concesionario.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final User authenticatedUser;

    public User getUserByUsername(String username) {
        return userRepository.findAllByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User with username " + username + " does not exist"));
    }

    public UserNameAndAdminDTO getThisUser() {
        UserNameAndAdminDTO userNameDTO = new UserNameAndAdminDTO();
        userNameDTO.setUsername(authenticatedUser.getUsername());
        userNameDTO.setRol(authenticatedUser.getRol());
        return userNameDTO;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }


    public String register(RegisterDTO registerRequest) {
        var user = new User().builder()
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .rol(Rol.client)
            .build();

        user.setEmail(registerRequest.getEmail());
        user.setRol(registerRequest.getRol());
        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    public String login(LoginDTO registerRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    registerRequest.getUsername(),
                    registerRequest.getPassword()
            )
        );

        User user = userRepository.findTopByUsername(registerRequest.getUsername()).orElseThrow();

        return jwtService.generateToken(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String name) {
       return userRepository.findTopByUsername(name).orElse(null);
    }
}
