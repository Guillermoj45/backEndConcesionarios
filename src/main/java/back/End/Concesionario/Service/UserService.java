package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.LoginDTO; // Importa el DTO para el login
import back.End.Concesionario.DTO.RegisterDTO; // Importa el DTO para el registro
import back.End.Concesionario.DTO.UserNameAndAdminDTO; // Importa el DTO para obtener el nombre de usuario y rol
import back.End.Concesionario.Model.Enum.Rol; // Importa el Enum que define los roles de los usuarios
import back.End.Concesionario.Model.User; // Importa la entidad User (Usuario)
import back.End.Concesionario.Repository.UserRepository; // Importa el repositorio para los usuarios
import back.End.Concesionario.security.JwtService; // Importa el servicio para la gestión de tokens JWT
import lombok.AllArgsConstructor; // Anota la clase para generar un constructor con todas las dependencias
import org.springframework.security.authentication.AuthenticationManager; // Importa el AuthenticationManager de Spring Security
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Importa la clase para manejar autenticaciones
import org.springframework.security.crypto.password.PasswordEncoder; // Importa el PasswordEncoder para codificar contraseñas
import org.springframework.stereotype.Service; // Marca la clase como un servicio de Spring

import java.util.List; // Importa la interfaz List para trabajar con colecciones

@Service // Indica que esta clase es un servicio que gestionará la lógica de negocio relacionada con los usuarios
@AllArgsConstructor // Genera un constructor con las dependencias necesarias para inyectarlas automáticamente
public class UserService {
    private final UserRepository userRepository; // Repositorio para interactuar con la base de datos de usuarios
    private final PasswordEncoder passwordEncoder; // Servicio para codificar contraseñas
    private final JwtService jwtService; // Servicio para generar y validar tokens JWT
    private final AuthenticationManager authenticationManager; // Administrador de autenticación
    private final User authenticatedUser; // Usuario autenticado (usado para obtener la información del usuario actualmente logueado)

    // Obtiene un usuario por su nombre de usuario
    public User getUserByUsername(String username) {
        return userRepository.findAllByUsername(username) // Busca al usuario por su nombre
                .orElseThrow(() -> new IllegalStateException("User with username " + username + " does not exist")); // Lanza excepción si no existe
    }

    // Obtiene el nombre de usuario y el rol del usuario autenticado
    public UserNameAndAdminDTO getThisUser() {
        UserNameAndAdminDTO userNameDTO = new UserNameAndAdminDTO(); // Crea un objeto DTO
        userNameDTO.setUsername(authenticatedUser.getUsername()); // Establece el nombre de usuario
        userNameDTO.setRol(authenticatedUser.getRol()); // Establece el rol del usuario autenticado
        return userNameDTO; // Devuelve el DTO con la información
    }

    // Obtiene todos los usuarios
    public List<User> getUsers() {
        return userRepository.findAll(); // Devuelve todos los usuarios de la base de datos
    }

    // Obtiene un usuario por su ID
    public User getUserById(Long id) {
        return userRepository.findById(id) // Busca el usuario por su ID
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist")); // Lanza excepción si no existe
    }

    // Actualiza un usuario
    public User updateUser(User user) {
        return userRepository.save(user); // Guarda los cambios del usuario en la base de datos
    }

    // Registra un nuevo usuario en el sistema
    public String register(RegisterDTO registerRequest) {
        var user = new User().builder() // Crea un nuevo objeto User usando el builder
            .username(registerRequest.getUsername()) // Establece el nombre de usuario
            .password(passwordEncoder.encode(registerRequest.getPassword())) // Codifica la contraseña
            .rol(Rol.client) // Establece el rol como "cliente" por defecto
            .build(); // Construye el objeto usuario

        user.setEmail(registerRequest.getEmail()); // Establece el email del usuario
        user.setRol(registerRequest.getRol()); // Establece el rol del usuario
        userRepository.save(user); // Guarda el nuevo usuario en la base de datos

        return jwtService.generateToken(user); // Genera y devuelve el token JWT para el usuario registrado
    }

    // Inicia sesión de un usuario
    public String login(LoginDTO registerRequest) {
        authenticationManager.authenticate( // Autentica al usuario con el nombre de usuario y la contraseña
            new UsernamePasswordAuthenticationToken(
                    registerRequest.getUsername(),
                    registerRequest.getPassword()
            )
        );

        User user = userRepository.findTopByUsername(registerRequest.getUsername()) // Obtiene el usuario autenticado
                .orElseThrow(); // Lanza excepción si no se encuentra el usuario

        return jwtService.generateToken(user); // Genera y devuelve el token JWT para el usuario autenticado
    }

    // Elimina un usuario por su ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Elimina al usuario de la base de datos por su ID
    }

    // Busca un usuario por su nombre de usuario
    public User findByUsername(String name) {
       return userRepository.findTopByUsername(name).orElse(null); // Devuelve el usuario si existe, o null si no
    }
}
