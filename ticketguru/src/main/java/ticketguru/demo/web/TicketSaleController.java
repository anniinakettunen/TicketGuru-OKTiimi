package ticketguru.demo.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.Event;
import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketType;
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
    
    @Autowired
    private TicketRepository ticketRepository;

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
    public String saveTicketSale(@RequestParam(required = false) Long userId, 
                               @RequestParam(required = false) Long eventId,
                               @RequestParam(required = false) String dateTime,
                               @RequestParam(required = false) String price,
                               @RequestParam Map<String, String> allParams) {
        try {
            System.out.println("DEBUG: Received parameters:");
            System.out.println("userId: " + userId);
            System.out.println("eventId: " + eventId);
            System.out.println("dateTime: " + dateTime);
            System.out.println("price: " + price);
            
            // Validate required parameters
            if (userId == null) {
                throw new RuntimeException("User ID is required");
            }
            if (eventId == null) {
                throw new RuntimeException("Event ID is required");
            }
            if (dateTime == null || dateTime.isEmpty()) {
                throw new RuntimeException("DateTime is required");
            }
            if (price == null || price.isEmpty()) {
                throw new RuntimeException("Price is required");
            }
            
            // Create new TicketSale
            TicketSale ticketSale = new TicketSale();
            
            // Set user
            AppUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            ticketSale.setUser(user);
            
            // Set datetime
            java.time.LocalDateTime parsedDateTime;
            if (dateTime.contains("T")) {
                parsedDateTime = java.time.LocalDateTime.parse(dateTime);
            } else {
                parsedDateTime = java.time.LocalDateTime.now();
            }
            ticketSale.setDateTime(parsedDateTime);
            
            // Set price
            java.math.BigDecimal parsedPrice = new java.math.BigDecimal(price);
            ticketSale.setPrice(parsedPrice);
            
            System.out.println("DEBUG: About to save TicketSale: " + ticketSale);
            
            // Save the ticket sale first
            TicketSale savedTicketSale = ticketSaleRepository.save(ticketSale);
            
            System.out.println("DEBUG: TicketSale saved successfully with ID: " + savedTicketSale.getSaleId());
            
            // Get the event
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            
            // Process ticket quantities and create individual tickets
            int totalTicketsCreated = 0;
            for (Map.Entry<String, String> entry : allParams.entrySet()) {
                String paramName = entry.getKey();
                String paramValue = entry.getValue();
                
                // Check if this parameter is a ticket quantity
                if (paramName.startsWith("ticketQuantities[") && paramName.endsWith("]")) {
                    // Extract ticket type ID from parameter name like "ticketQuantities[1]"
                    String ticketTypeIdStr = paramName.substring(17, paramName.length() - 1);
                    Long ticketTypeId = Long.parseLong(ticketTypeIdStr);
                    int quantity = Integer.parseInt(paramValue);
                    
                    System.out.println("DEBUG: Creating " + quantity + " tickets of type " + ticketTypeId);
                    
                    if (quantity > 0) {
                        // Get the ticket type
                        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                                .orElseThrow(() -> new RuntimeException("TicketType not found: " + ticketTypeId));
                        
                        // Create individual tickets for this type
                        for (int i = 0; i < quantity; i++) {
                            Ticket ticket = new Ticket();
                            ticket.setTicketSale(savedTicketSale);
                            ticket.setEventId(event);
                            ticket.setTicketTypeId(ticketType);
                            
                            // Generate a unique ticket code
                            ticket.setTicketCode(System.currentTimeMillis() + i);
                            
                            ticketRepository.save(ticket);
                            totalTicketsCreated++;
                        }
                    }
                }
            }
            
            System.out.println("DEBUG: Created " + totalTicketsCreated + " tickets successfully");
            return "home";
            
        } catch (Exception e) {
            System.err.println("ERROR saving ticket sale: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/api/ticketsales/newsale?error=" + e.getMessage();
        }
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

    @DeleteMapping("/ticketsales/{saleId}")  // DELETE /api/ticketsales/{id}
    @ResponseBody
    public ResponseEntity<Void> deleteTicketSaleJson(@PathVariable Long saleId) {
        if (!ticketSaleRepository.existsById(saleId)) return ResponseEntity.notFound().build();
        ticketSaleRepository.deleteById(saleId);
        return ResponseEntity.noContent().build();
    }
}
