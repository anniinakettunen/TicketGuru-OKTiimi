package ticketguru.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketguru.demo.domain.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    
    Optional<AppUser> findByEmail(String email);

    AppUser findByFirstnameAndLastname(String firstname, String lastname);

    boolean existsByRole_RoleId(Long roleId);
}
