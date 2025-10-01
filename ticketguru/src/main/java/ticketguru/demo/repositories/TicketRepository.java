package ticketguru.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ticketguru.demo.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
 
}
