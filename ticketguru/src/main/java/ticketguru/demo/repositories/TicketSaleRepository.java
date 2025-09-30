package ticketguru.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.domain.User;

public interface TicketSaleRepository extends JpaRepository<TicketSale, Long> {
    
    List<TicketSale> findByUserId(User userId);

    List<TicketSale> findBySaleId(TicketSale saleId);

    
}
