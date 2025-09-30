package ticketguru.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.demo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleName(String roleName);

}
