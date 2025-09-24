package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ticketguru.demo.domain.Role;
import ticketguru.demo.repositories.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    private RoleRepository roleRepository;

    
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

 
    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setRoleName(updatedRole.getRoleName());
                    role.setNotes(updatedRole.getNotes());
                    return roleRepository.save(role);
                })
                .orElseGet(() -> {
                    updatedRole.setRoleId(id); // Varmista että tämä metodi on olemassa entiteetissä
                    return roleRepository.save(updatedRole);
                });
    }

   
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
    }
}
