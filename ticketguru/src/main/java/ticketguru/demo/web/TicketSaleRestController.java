package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.repositories.TicketSaleRepository;
import ticketguru.demo.repositories.UserRepository;

@RestController
@RequestMapping("/ticketsales/api")
public class TicketSaleRestController {

    @Autowired
    private TicketSaleRepository ticketSaleRepository;

    @Autowired
    private UserRepository userRepository;

    // GET: Hae kaikki ticket salet
    @GetMapping
    public List<TicketSale> getAllTicketSales() {
        return ticketSaleRepository.findAll();
    }

    // GET: Hae ticket sale ID:n perusteella
    @GetMapping("/{saleId}")
    public ResponseEntity<TicketSale> getTicketSaleById(@PathVariable Long saleId) {
        Optional<TicketSale> ticketSale = ticketSaleRepository.findById(saleId);
        return ticketSale.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Hae kaikki ticket salet tietylle käyttäjälle
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketSale>> getTicketSalesByUser(@PathVariable Long userId) {
        Optional<AppUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AppUser user = optionalUser.get();
        List<TicketSale> sales = ticketSaleRepository.findByUser(user);
        return ResponseEntity.ok(sales);
    }

    // POST: Luo uusi ticket sale
    @PostMapping
    public ResponseEntity<TicketSale> createTicketSale(@RequestBody TicketSale ticketSale) {
        if (ticketSale.getUser() != null && ticketSale.getUser().getId() != null) {
            AppUser user = userRepository.findById(ticketSale.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + ticketSale.getUser().getId()));
            ticketSale.setUser(user);
        } else {
            throw new RuntimeException("TicketSale must have a valid user");
        }
        TicketSale savedSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(savedSale);
    }

    // PUT: Päivitä ticket sale ID:n perusteella
    @PutMapping("/{saleId}")
    public ResponseEntity<TicketSale> updateTicketSale(@PathVariable Long saleId,
                                                       @RequestBody TicketSale ticketSaleDetails) {
        Optional<TicketSale> optionalTicketSale = ticketSaleRepository.findById(saleId);
        if (optionalTicketSale.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TicketSale ticketSale = optionalTicketSale.get();
        ticketSale.setDateTime(ticketSaleDetails.getDateTime());
        ticketSale.setPrice(ticketSaleDetails.getPrice());

        if (ticketSaleDetails.getUser() != null && ticketSaleDetails.getUser().getId() != null) {
            AppUser user = userRepository.findById(ticketSaleDetails.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + ticketSaleDetails.getUser().getId()));
            ticketSale.setUser(user);
        }

        TicketSale updatedTicketSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(updatedTicketSale);
    }

    // DELETE: Poista ticket sale ID:n perusteella
    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteTicketSale(@PathVariable Long saleId) {
        if (!ticketSaleRepository.existsById(saleId)) {
            return ResponseEntity.notFound().build();
        }
        ticketSaleRepository.deleteById(saleId);
        return ResponseEntity.noContent().build();
    }
}
