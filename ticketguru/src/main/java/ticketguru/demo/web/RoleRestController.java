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
import ticketguru.demo.domain.Role;
import ticketguru.demo.repositories.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    private RoleRepository roleRepository;

    // GET: Hae kaikki roolit
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // GET: Hae rooli ID:n perusteella
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Luo uusi rooli
    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.ok(savedRole);
    }

    // PUT: Päivitä rooli ID:n perusteella
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @Valid @RequestBody Role updatedRole) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Role role = optionalRole.get();
        role.setRoleName(updatedRole.getRoleName());
        role.setNotes(updatedRole.getNotes());
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.ok(savedRole);
    }

    // DELETE: Poista rooli ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (!roleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        roleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
