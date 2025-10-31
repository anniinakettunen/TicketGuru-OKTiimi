package ticketguru.demo.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketType;
import ticketguru.demo.domain.Event;

import ticketguru.demo.repositories.TicketRepository;
import ticketguru.demo.repositories.TicketTypeRepository;
import ticketguru.demo.repositories.EventRepository;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    // Get all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get ticket by ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new ticket
    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticketDetails) {
        if (ticketDetails.getTicketTypeId() == null || ticketDetails.getTicketTypeId().getTicketTypeId() == null) {
            return ResponseEntity.badRequest().body("Ticket must have a valid ticketTypeId");
        }
        TicketType ticketType = ticketTypeRepository.findById(ticketDetails.getTicketTypeId().getTicketTypeId())
                .orElse(null);
        if (ticketType == null) return ResponseEntity.badRequest().body("TicketType not found");

        if (ticketDetails.getEventId() == null || ticketDetails.getEventId().getEventId() == null) {
            return ResponseEntity.badRequest().body("Ticket must have a valid eventId");
        }
        Event event = eventRepository.findById(ticketDetails.getEventId().getEventId())
                .orElse(null);
        if (event == null) return ResponseEntity.badRequest().body("Event not found");

        Ticket ticket = new Ticket();
        ticket.setTicketCode(ticketDetails.getTicketCode());
        ticket.setTicketTypeId(ticketType);
        ticket.setEventId(event);

        Ticket savedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    // Update an existing ticket
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) return ResponseEntity.notFound().build();

        if (ticketDetails.getTicketCode() != null) {
            ticket.setTicketCode(ticketDetails.getTicketCode());
        }

        if (ticketDetails.getTicketTypeId() != null && ticketDetails.getTicketTypeId().getTicketTypeId() != null) {
            TicketType ticketType = ticketTypeRepository.findById(
                    ticketDetails.getTicketTypeId().getTicketTypeId()).orElse(null);
            if (ticketType == null) return ResponseEntity.badRequest().body("TicketType not found");
            ticket.setTicketTypeId(ticketType);
        } else {
            return ResponseEntity.badRequest().body("Ticket must have a valid ticketTypeId");
        }

        if (ticketDetails.getEventId() != null && ticketDetails.getEventId().getEventId() != null) {
            Event event = eventRepository.findById(ticketDetails.getEventId().getEventId()).orElse(null);
            if (event == null) return ResponseEntity.badRequest().body("Event not found");
            ticket.setEventId(event);
        } else {
            return ResponseEntity.badRequest().body("Ticket must have a valid eventId");
        }

        Ticket updatedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    // Delete a ticket
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) return ResponseEntity.notFound().build();
        ticketRepository.deleteById(ticketId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkTicket(@RequestParam String ticketCode) {
    try {
        Long code = Long.parseLong(ticketCode);
        return ticketRepository.findByTicketCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    } catch (NumberFormatException e) {
        return ResponseEntity.badRequest().body("Invalid ticketCode format");
    }
}

}
