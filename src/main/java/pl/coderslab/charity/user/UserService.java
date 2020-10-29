package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void registerUser(User user) {
        user.setRole(Role.ROLE_USER.toString());;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    public void createUserAdmin() {
        User userAdmin = new User();
        userAdmin.setFirstName("Admin");
        userAdmin.setLastName("Admin");
        userAdmin.setActive(true);
        userAdmin.setPassword(passwordEncoder.encode("adminadmin"));
        userAdmin.setRepeatPassword("adminadmin");
        userAdmin.setRole(Role.ROLE_ADMIN.toString());
        userAdmin.setEmail("admin@gmail.com");
        userRepository.save(userAdmin);
    }
}
