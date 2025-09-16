package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.repositories.TicketSaleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticketsales")
public class TicketSaleRestController {

    @Autowired
    private TicketSaleRepository ticketSaleRepository;

    // GET: Hae kaikki ticket salet
    @GetMapping
    public List<TicketSale> getAllTicketSales() {
        return ticketSaleRepository.findAll();
    }

    // GET: Hae ticket sale ID:n perusteella
    @GetMapping("/{id}")
    public ResponseEntity<TicketSale> getTicketSaleById(@PathVariable Long id) {
        Optional<TicketSale> ticketSale = ticketSaleRepository.findById(id);
        return ticketSale.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Luo uusi ticket sale
    @PostMapping
    public TicketSale createTicketSale(@RequestBody TicketSale ticketSale) {
        return ticketSaleRepository.save(ticketSale);
    }

    // PUT: Päivitä ticket sale ID:n perusteella
    @PutMapping("/{id}")
    public ResponseEntity<TicketSale> updateTicketSale(@PathVariable Long id, @RequestBody TicketSale ticketSaleDetails) {
        Optional<TicketSale> optionalTicketSale = ticketSaleRepository.findById(id);
        if (optionalTicketSale.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TicketSale ticketSale = optionalTicketSale.get();
        ticketSale.setDateTime(ticketSaleDetails.getDateTime());
        ticketSale.setPrice(ticketSaleDetails.getPrice());
        ticketSale.setUserId(ticketSaleDetails.getUserId());
        TicketSale updatedTicketSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(updatedTicketSale);
    }

    // DELETE: Poista ticket sale ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketSale(@PathVariable Long id) {
        if (!ticketSaleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticketSaleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
