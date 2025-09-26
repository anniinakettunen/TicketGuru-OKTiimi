package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    // GET: Hae kaikki käyttäjät
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // GET: Hae käyttäjä ID:n perusteella
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Luo uusi käyttäjä
    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return userRepository.save(user);
    }

    // PUT: Päivitä käyttäjä ID:n perusteella
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long userId, @RequestBody AppUser userDetails) {
        Optional<AppUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AppUser user = optionalUser.get();
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setRole(userDetails.getRole());
        AppUser updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE: Poista käyttäjä ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
