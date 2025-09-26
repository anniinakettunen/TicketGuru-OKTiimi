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

import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.repositories.TicketSaleRepository;

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
    public ResponseEntity<TicketSale> getTicketSaleById(@PathVariable Long saleId) {
        Optional<TicketSale> ticketSale = ticketSaleRepository.findById(saleId);
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
    public ResponseEntity<TicketSale> updateTicketSale(@PathVariable Long saleId, @RequestBody TicketSale ticketSaleDetails) {
        Optional<TicketSale> optionalTicketSale = ticketSaleRepository.findById(saleId);
        if (optionalTicketSale.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TicketSale ticketSale = optionalTicketSale.get();
        ticketSale.setDateTime(ticketSaleDetails.getDateTime());
        ticketSale.setPrice(ticketSaleDetails.getPrice());
        ticketSale.setUserId(ticketSaleDetails.getId());
        TicketSale updatedTicketSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(updatedTicketSale);
    }

    // DELETE: Poista ticket sale ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketSale(@PathVariable Long saleId) {
        if (!ticketSaleRepository.existsById(saleId)) {
            return ResponseEntity.notFound().build();
        }
        ticketSaleRepository.deleteById(saleId);
        return ResponseEntity.noContent().build();
    }
}
