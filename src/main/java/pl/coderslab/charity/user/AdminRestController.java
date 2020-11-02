package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.institution.Institution;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllAdmins() {
        return userService.getAllAdmins(Role.ROLE_ADMIN.toString());
    }

    @PostMapping
    public ResponseEntity saveAdmin(@Valid @RequestBody User user) {
        userService.createAdmin(user);
        return ResponseEntity.created(URI.create("/api/admin/" + user.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable long id) {
        Optional<User> user = userService.findUserById(id);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveEditedAdmin(@PathVariable long id, @Valid @RequestBody User user) {
        if (user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Błędne id");
        }
        userService.createAdmin(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdminById(@PathVariable long id){
        User user = userService.findUserByIdAndRole(id, Role.ROLE_ADMIN.toString());
        if (user != null) {
            userService.deleteAdminById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
