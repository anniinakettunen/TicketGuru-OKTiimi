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
    public ResponseEntity<?> createSale(@Valid @RequestBody TicketSale ticketSale, @RequestParam(required = false) String buyerEmail) {

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

        // 3️⃣ Save sale first
        TicketSale savedSale = ticketSaleRepository.save(ticketSale);

        // 4️⃣ Validate & save tickets
        for (Ticket ticket : ticketSale.getTickets()) {

            // Validate event
            if (ticket.getEventId() == null || ticket.getEventId().getEventId() == null) {
                return ResponseEntity.badRequest().body("Ticket must have an eventId");
            }
            Event event = eventRepository.findById(ticket.getEventId().getEventId()).orElse(null);
            if (event == null) return ResponseEntity.badRequest().body("Event not found");
            ticket.setEventId(event);

            // Validate ticket type
            if (ticket.getTicketTypeId() == null || ticket.getTicketTypeId().getTicketTypeId() == null) {
                return ResponseEntity.badRequest().body("Ticket must have a ticketTypeId");
            }
            TicketType type = ticketTypeRepository.findById(ticket.getTicketTypeId().getTicketTypeId()).orElse(null);
            if (type == null) return ResponseEntity.badRequest().body("TicketType not found");
            ticket.setTicketTypeId(type);

            // Associate with sale
            ticket.setTicketSale(savedSale);

            // Generate ticket code if missing
            if (ticket.getTicketCode() == null) {
                ticket.setTicketCode(System.currentTimeMillis());
            }

            ticketRepository.save(ticket);
        }

        // Refresh the sale to get all tickets with their IDs
        savedSale = ticketSaleRepository.findById(savedSale.getSaleId()).orElse(savedSale);

        // Send email with ticket codes to customer
        // Use buyerEmail parameter if provided, otherwise fall back to user's email
        String emailAddress = (buyerEmail != null && !buyerEmail.isEmpty()) ? buyerEmail : user.getEmail();
        System.out.println("Attempting to send email to: " + emailAddress);
        System.out.println("Tickets in sale: " + (savedSale.getTickets() != null ? savedSale.getTickets().size() : 0));
        
        if (emailAddress != null && !emailAddress.isEmpty()) {
            emailService.sendTicketEmail(savedSale, emailAddress);
        } else {
            System.err.println("No email address provided for sending tickets!");
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
