package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.Role;
import ticketguru.demo.repositories.UserRepository;
import ticketguru.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    // GET: Hae kaikki käyttäjät
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // GET: Hae käyttäjä ID:n perusteella
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Luo uusi käyttäjä
    @PostMapping
public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
    if (user.getRole() != null && user.getRole().getRoleId() != null) {
        Long roleId = user.getRole().getRoleId();
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
        user.setRole(role); 
    }
    AppUser savedUser = userRepository.save(user);
    return ResponseEntity.ok(savedUser);
}
    // PUT: Päivitä käyttäjä ID:n perusteella
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Long userId, @RequestBody AppUser userDetails) {
        Optional<AppUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AppUser user = optionalUser.get();
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        if (userDetails.getRole() != null && userDetails.getRole().getRoleId() != null) {
            Long roleId = userDetails.getRole().getRoleId();
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
            user.setRole(role);
        }
        AppUser updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE: Poista käyttäjä ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
