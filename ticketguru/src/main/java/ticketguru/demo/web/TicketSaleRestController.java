package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ticketguru.demo.domain.*;
import ticketguru.demo.repositories.*;
import ticketguru.demo.service.TicketsToEmailService;

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

    @Autowired
    private TicketsToEmailService emailService;

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
public ResponseEntity<?> createSale(@RequestBody TicketSale ticketSale,
                                    @RequestParam(required = false) String buyerEmail) {

    // 1️⃣ Validate user
    if (ticketSale.getUser() == null || ticketSale.getUser().getId() == null) {
        return ResponseEntity.badRequest().body("TicketSale must have a valid user");
    }
    AppUser user = userRepository.findById(ticketSale.getUser().getId()).orElse(null);
    if (user == null) return ResponseEntity.badRequest().body("User not found");
    ticketSale.setUser(user);

    // 2️⃣ Validate tickets
    if (ticketSale.getTickets() == null || ticketSale.getTickets().isEmpty()) {
        return ResponseEntity.badRequest().body("TicketSale must have at least one ticket");
    }

    // 3️⃣ Save sale first (tickets will be updated after)
    TicketSale savedSale = ticketSaleRepository.save(ticketSale);

    // 4️⃣ Loop through tickets
    for (Ticket ticket : ticketSale.getTickets()) {

        // Fetch Event by ID
        if (ticket.getEventId() == null) return ResponseEntity.badRequest().body("Ticket must have eventId");
        Event event = eventRepository.findById(ticket.getEventId().getEventId()).orElse(null);
        if (event == null) return ResponseEntity.badRequest().body("Event not found");
        ticket.setEventId(event);

        // Fetch TicketType by ID
        if (ticket.getTicketTypeId() == null) return ResponseEntity.badRequest().body("Ticket must have ticketTypeId");
        TicketType type = ticketTypeRepository.findById(ticket.getTicketTypeId().getTicketTypeId()).orElse(null);
        if (type == null) return ResponseEntity.badRequest().body("TicketType not found");
        ticket.setTicketTypeId(type);

        // Associate ticket with sale
        ticket.setTicketSale(savedSale);

        // Generate ticketCode if missing
        if (ticket.getTicketCode() == null) {
            ticket.setTicketCode(System.currentTimeMillis());
        }

        ticketRepository.save(ticket);
    }

    // Refresh sale to include tickets
    savedSale = ticketSaleRepository.findById(savedSale.getSaleId()).orElse(savedSale);

   // Send email (NON-CRITICAL)
    String emailAddress = (buyerEmail != null && !buyerEmail.isEmpty())
        ? buyerEmail
        : user.getEmail();

    if (emailAddress != null && !emailAddress.isEmpty()) {
        try {
            emailService.sendTicketEmail(savedSale, emailAddress);
        } catch (Exception e) {
            System.out.println("Email failed, sale still successful: " + e.getMessage());
        }
    }


    return ResponseEntity.ok(savedSale);
}

    
    // ===== UPDATE sale =====
    @PutMapping("/{saleId}")
    @Transactional
    public ResponseEntity<?> updateSale(@PathVariable Long saleId, @Valid @RequestBody TicketSale saleDetails) {
        TicketSale sale = ticketSaleRepository.findById(saleId).orElse(null);
        if (sale == null) return ResponseEntity.notFound().build();

        // Update basic fields
        sale.setDateTime(saleDetails.getDateTime());
        sale.setPrice(saleDetails.getPrice());

        // Update user if provided
        if (saleDetails.getUser() != null && saleDetails.getUser().getId() != null) {
            AppUser user = userRepository.findById(saleDetails.getUser().getId()).orElse(null);
            if (user == null) return ResponseEntity.badRequest().body("User not found");
            sale.setUser(user);
        }

        // Validate tickets (cannot be empty)
        if (saleDetails.getTickets() == null || saleDetails.getTickets().isEmpty()) {
            return ResponseEntity.badRequest().body("TicketSale must have at least one ticket");
        }

        // Update tickets
        for (Ticket updatedTicket : saleDetails.getTickets()) {
            Ticket ticket;

            if (updatedTicket.getTicketId() != null) {
                ticket = ticketRepository.findById(updatedTicket.getTicketId()).orElse(null);
                if (ticket == null) return ResponseEntity.badRequest().body("Ticket not found");
            } else {
                ticket = new Ticket();
                ticket.setTicketSale(sale);
            }

            ticket.setTicketCode(updatedTicket.getTicketCode());

            // Validate event
            if (updatedTicket.getEventId() == null || updatedTicket.getEventId().getEventId() == null)
                return ResponseEntity.badRequest().body("Ticket must have an eventId");
            Event event = eventRepository.findById(updatedTicket.getEventId().getEventId()).orElse(null);
            if (event == null) return ResponseEntity.badRequest().body("Event not found");
            ticket.setEventId(event);

            // Validate ticket type
            if (updatedTicket.getTicketTypeId() == null || updatedTicket.getTicketTypeId().getTicketTypeId() == null)
                return ResponseEntity.badRequest().body("Ticket must have a ticketTypeId");
            TicketType type = ticketTypeRepository.findById(updatedTicket.getTicketTypeId().getTicketTypeId()).orElse(null);
            if (type == null) return ResponseEntity.badRequest().body("TicketType not found");
            ticket.setTicketTypeId(type);

            ticketRepository.save(ticket);
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
