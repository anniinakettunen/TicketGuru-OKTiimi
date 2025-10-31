package ticketguru.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ticketguru.demo.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketCode(Long ticketCode);
}