package ticketguru.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.demo.domain.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

}
