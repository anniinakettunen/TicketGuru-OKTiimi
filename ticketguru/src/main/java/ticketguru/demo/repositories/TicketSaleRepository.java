package ticketguru.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.TicketSale;

public interface TicketSaleRepository extends JpaRepository<TicketSale, Long> {

    List<TicketSale> findByUser(AppUser user);

    List<TicketSale> findBySaleId(TicketSale saleId);
}
