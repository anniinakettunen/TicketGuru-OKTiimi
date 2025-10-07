package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.Role;
import ticketguru.demo.repositories.RoleRepository;
import ticketguru.demo.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // GET all users
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create user
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser user) {
        // Validate role
        if (user.getRole() == null || user.getRole().getRoleId() == null) {
            return ResponseEntity.badRequest().body("Role must be provided with a valid roleId");
        }

        Role role = roleRepository.findById(user.getRole().getRoleId())
                .orElse(null);
        if (role == null) {
            return ResponseEntity.badRequest().body("Role not found with id " + user.getRole().getRoleId());
        }
        user.setRole(role);

        AppUser savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // PUT: Update user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody AppUser userDetails) {
        AppUser user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        // Update basic fields
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());

        // Validate role
        if (userDetails.getRole() == null || userDetails.getRole().getRoleId() == null) {
            return ResponseEntity.badRequest().body("Role must be provided with a valid roleId");
        }

        Role role = roleRepository.findById(userDetails.getRole().getRoleId())
                .orElse(null);
        if (role == null) return ResponseEntity.badRequest().body("Role not found with id " + userDetails.getRole().getRoleId());
        user.setRole(role);

        AppUser updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE: Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) return ResponseEntity.notFound().build();

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
