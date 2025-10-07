package ticketguru.demo.web;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/{ticketId}")
    public Optional<Ticket> getTicketById(@PathVariable Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticketDetails.getTicketCode() != null) {
            ticket.setTicketCode(ticketDetails.getTicketCode());
        }

        if (ticketDetails.getTicketTypeId() != null && ticketDetails.getTicketTypeId().getTicketTypeId() != null) {
            TicketType ticketType = ticketTypeRepository.findById(
                    ticketDetails.getTicketTypeId().getTicketTypeId())
                    .orElseThrow(() -> new RuntimeException("TicketType not found"));
            ticket.setTicketTypeId(ticketType);
        }

        if (ticketDetails.getEventId() != null && ticketDetails.getEventId().getEventId() != null) {
            Event event = eventRepository.findById(
                    ticketDetails.getEventId().getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            ticket.setEventId(event);
        }

        Ticket updatedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
