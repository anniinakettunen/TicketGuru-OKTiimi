package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.repositories.*;

@Controller  // Use @Controller to allow returning views AND JSON
@RequestMapping("/api")
public class TicketSaleController {

    @Autowired
    private TicketSaleRepository ticketSaleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    // ===== Thymeleaf endpoints (HTML) =====
    @GetMapping("/ticketsales/newsale") // GET /api/ticketsales/newsale
    public String getNewSaleForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("tickettypes", ticketTypeRepository.findAll());
        model.addAttribute("ticketSale", new TicketSale());
        return "newsale";
    }

    @PostMapping("/ticketsales/savesale") // POST /api/ticketsales/savesale
    public String saveTicketSale(@ModelAttribute TicketSale ticketSale, @RequestParam Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ticketSale.setUser(user);
        ticketSaleRepository.save(ticketSale);
        return "home";
    }

    // ===== JSON REST API endpoints =====
    @GetMapping("/ticketsales")  // GET /api/ticketsales
    @ResponseBody
    public List<TicketSale> getAllTicketSalesJson() {
        return ticketSaleRepository.findAll();
    }

    @GetMapping("/ticketsales/{saleId}")  // GET /api/ticketsales/{id}
    @ResponseBody
    public ResponseEntity<TicketSale> getTicketSaleByIdJson(@PathVariable Long saleId) {
        Optional<TicketSale> ticketSale = ticketSaleRepository.findById(saleId);
        return ticketSale.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/ticketsales")  // POST /api/ticketsales
    @ResponseBody
    public ResponseEntity<TicketSale> createTicketSaleJson(@RequestBody TicketSale ticketSale) {
        if (ticketSale.getUser() != null && ticketSale.getUser().getId() != null) {
            AppUser user = userRepository.findById(ticketSale.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            ticketSale.setUser(user);
        } else {
            throw new RuntimeException("TicketSale must have a valid user");
        }
        TicketSale savedSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(savedSale);
    }

    @PutMapping("/ticketsales/{saleId}")  // PUT /api/ticketsales/{id}
    @ResponseBody
    public ResponseEntity<TicketSale> updateTicketSaleJson(@PathVariable Long saleId,
                                                           @RequestBody TicketSale ticketSaleDetails) {
        Optional<TicketSale> optionalTicketSale = ticketSaleRepository.findById(saleId);
        if (optionalTicketSale.isEmpty()) return ResponseEntity.notFound().build();

        TicketSale ticketSale = optionalTicketSale.get();
        ticketSale.setDateTime(ticketSaleDetails.getDateTime());
        ticketSale.setPrice(ticketSaleDetails.getPrice());

        if (ticketSaleDetails.getUser() != null && ticketSaleDetails.getUser().getId() != null) {
            AppUser user = userRepository.findById(ticketSaleDetails.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            ticketSale.setUser(user);
        }

        TicketSale updatedTicketSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(updatedTicketSale);
    }

    @DeleteMapping("/ticketsales/{saleId}")  // DELETE /api/Sticketsales/{id}
    @ResponseBody
    public ResponseEntity<Void> deleteTicketSaleJson(@PathVariable Long saleId) {
        if (!ticketSaleRepository.existsById(saleId)) return ResponseEntity.notFound().build();
        ticketSaleRepository.deleteById(saleId);
        return ResponseEntity.noContent().build();
    }
}
