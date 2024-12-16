package back.End.Concesionario.Controller;

// Importaciones necesarias para manejar DTOs, servicios y anotaciones de Spring
import back.End.Concesionario.DTO.LoginDTO; // DTO para los datos de inicio de sesión
import back.End.Concesionario.DTO.RegisterDTO; // DTO para el registro de usuarios
import back.End.Concesionario.DTO.UserNameAndAdminDTO; // DTO que contiene información del usuario y si es administrador
import back.End.Concesionario.Model.User; // Modelo de usuario
import back.End.Concesionario.Service.UserService; // Servicio que contiene la lógica de negocio relacionada con usuarios
import lombok.AllArgsConstructor; // Genera un constructor con todas las propiedades
import org.springframework.web.bind.annotation.*; // Anotaciones para manejar las solicitudes HTTP

import java.util.List; // Importación para manejar listas

// Marca esta clase como un controlador REST que gestiona solicitudes HTTP relacionadas con usuarios
@RestController
// Asigna un prefijo base "api/user" para todas las rutas de este controlador
@RequestMapping("api/user")
// Genera automáticamente un constructor con todas las propiedades declaradas como finales
@AllArgsConstructor
public class UserController {

    // Dependencia del servicio que gestiona la lógica de negocio de los usuarios
    private final UserService userService;

    // Maneja solicitudes GET a "api/user/username/{username}" para obtener un usuario por su nombre de usuario
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        // Llama al servicio para buscar y devolver un usuario por su nombre de usuario
        return userService.getUserByUsername(username);
    }

    // Maneja solicitudes POST a "api/user/login" para iniciar sesión con credenciales proporcionadas
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        // Llama al servicio para validar las credenciales y devolver un token o mensaje de éxito
        return userService.login(loginDTO);
    }

    // Maneja solicitudes GET a "api/user/this" para obtener los datos del usuario actualmente autenticado
    @GetMapping("this")
    public UserNameAndAdminDTO getThisUser() {
        // Llama al servicio para obtener información del usuario actual y si es administrador
        return userService.getThisUser();
    }

    // Maneja solicitudes GET a "api/user" para obtener la lista de todos los usuarios
    @GetMapping
    public List<User> getUsers() {
        // Llama al servicio para obtener una lista de todos los usuarios
        return userService.getUsers();
    }

    // Maneja solicitudes GET a "api/user/{id}" para obtener un usuario específico por su ID
    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        // Llama al servicio para buscar y devolver un usuario por su ID
        return userService.getUserById(id);
    }

    // Maneja solicitudes PUT a "api/user" para actualizar los datos de un usuario
    @PutMapping
    public User updateUser(@RequestBody User user) {
        // Llama al servicio para actualizar el usuario y devuelve el usuario actualizado
        return userService.updateUser(user);
    }

    // Maneja solicitudes POST a "api/user" para registrar un nuevo usuario
    @PostMapping
    public String addUser(@RequestBody RegisterDTO user) {
        // Llama al servicio para registrar un nuevo usuario con los datos proporcionados
        return userService.register(user);
    }

    // Maneja solicitudes DELETE a "api/user/{id}" para eliminar un usuario por su ID
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        // Llama al servicio para eliminar el usuario correspondiente al ID proporcionado
        userService.deleteUser(id);
    }
}
