package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ticketguru.demo.domain.*;
import ticketguru.demo.repositories.*;

@RestController
@RequestMapping("/api/ticketsales")
public class TicketSaleRestController {

    @Autowired
    private TicketSaleRepository ticketSaleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // ===== GET all sales =====
    @GetMapping
    public List<TicketSale> getAllSales() {
        return ticketSaleRepository.findAll();
    }

    // ===== GET sale by ID =====
    @GetMapping("/{saleId}")
    public ResponseEntity<TicketSale> getSaleById(@PathVariable Long saleId) {
        Optional<TicketSale> optionalSale = ticketSaleRepository.findById(saleId);
        return optionalSale.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ===== CREATE new sale =====
    @PostMapping
    @Transactional
    public ResponseEntity<TicketSale> createSale(@RequestBody TicketSale ticketSale) {
        // Set full user
        if (ticketSale.getUser() == null || ticketSale.getUser().getId() == null)
            throw new RuntimeException("TicketSale must have a valid user");

        AppUser user = userRepository.findById(ticketSale.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ticketSale.setUser(user);

        // Save sale first
        TicketSale savedSale = ticketSaleRepository.save(ticketSale);

        // Process tickets if provided
        if (ticketSale.getTickets() != null) {
            for (Ticket ticket : ticketSale.getTickets()) {
                Event event = eventRepository.findById(ticket.getEventId().getEventId())
                        .orElseThrow(() -> new RuntimeException("Event not found"));
                TicketType type = ticketTypeRepository.findById(ticket.getTicketTypeId().getTicketTypeId())
                        .orElseThrow(() -> new RuntimeException("TicketType not found"));

                ticket.setEventId(event);
                ticket.setTicketTypeId(type);
                ticket.setTicketSale(savedSale);

                // Generate ticket code if not provided
                if (ticket.getTicketCode() == null) {
                    ticket.setTicketCode(System.currentTimeMillis());
                }

                ticketRepository.save(ticket);
            }
        }

        return ResponseEntity.ok(savedSale);
    }

    // ===== UPDATE sale =====
    @PutMapping("/{saleId}")
    @Transactional
    public ResponseEntity<TicketSale> updateSale(@PathVariable Long saleId,
                                                 @RequestBody TicketSale saleDetails) {
        TicketSale sale = ticketSaleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("TicketSale not found"));

        sale.setDateTime(saleDetails.getDateTime());
        sale.setPrice(saleDetails.getPrice());

        if (saleDetails.getUser() != null && saleDetails.getUser().getId() != null) {
            AppUser user = userRepository.findById(saleDetails.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            sale.setUser(user);
        }

        if (saleDetails.getTickets() != null) {
            for (Ticket updatedTicket : saleDetails.getTickets()) {
                Ticket ticket;
                if (updatedTicket.getTicketId() != null) {
                    ticket = ticketRepository.findById(updatedTicket.getTicketId())
                            .orElseThrow(() -> new RuntimeException("Ticket not found"));
                } else {
                    ticket = new Ticket();
                    ticket.setTicketSale(sale);
                }

                ticket.setTicketCode(updatedTicket.getTicketCode());

                Event event = eventRepository.findById(updatedTicket.getEventId().getEventId())
                        .orElseThrow(() -> new RuntimeException("Event not found"));
                ticket.setEventId(event);

                TicketType type = ticketTypeRepository.findById(updatedTicket.getTicketTypeId().getTicketTypeId())
                        .orElseThrow(() -> new RuntimeException("TicketType not found"));
                ticket.setTicketTypeId(type);

                ticketRepository.save(ticket);
            }
        }

        TicketSale updatedSale = ticketSaleRepository.save(sale);
        return ResponseEntity.ok(updatedSale);
    }

    // ===== DELETE sale =====
    @DeleteMapping("/{saleId}")
    @Transactional
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        if (!ticketSaleRepository.existsById(saleId)) return ResponseEntity.notFound().build();

        ticketSaleRepository.deleteById(saleId);
        return ResponseEntity.noContent().build();
    }
}
