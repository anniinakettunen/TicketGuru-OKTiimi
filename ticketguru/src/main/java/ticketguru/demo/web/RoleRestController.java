package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ticketguru.demo.domain.Role;
import ticketguru.demo.repositories.RoleRepository;
import ticketguru.demo.repositories.UserRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository; 

    // GET all roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // GET role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create role
    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody Role role) {
        if (role.getRoleName() == null || role.getRoleName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Role name is required");
        }
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.ok(savedRole);
    }

    // PUT update role
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @Valid @RequestBody Role updatedRole) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()) return ResponseEntity.notFound().build();

        Role role = optionalRole.get();
        if (updatedRole.getRoleName() == null || updatedRole.getRoleName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Role name is required");
        }

        role.setRoleName(updatedRole.getRoleName());
        role.setNotes(updatedRole.getNotes());
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.ok(savedRole);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteRole(@PathVariable Long id) {
    if (!roleRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    if (userRepository.existsByRole_RoleId(id)) {
        return ResponseEntity
                .badRequest()
                .body("Role is assigned to users and cannot be deleted.");
    }

    roleRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}
}
