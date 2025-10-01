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

import ticketguru.demo.repositories.TicketRepository;
import ticketguru.demo.domain.Ticket;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    @Autowired
    private TicketRepository ticketrepository;

    // Get all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketrepository.findAll();
    }

    // Get ticket by ID
    @GetMapping("/{ticketId}")
    public Optional<Ticket> getTicketById(@PathVariable Long ticketId) {
        return ticketrepository.findById(ticketId);
    }

    // Create new ticket
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketrepository.save(ticket);
    }

    // Update ticket
    @PutMapping("/{ticketId}")
    public Ticket updateTicket(@PathVariable Long ticketId, @RequestBody Ticket updatedTicket) {
        return ticketrepository.findById(ticketId)
            .map(ticket -> {
                ticket.setTicketCode(updatedTicket.getTicketCode());
                ticket.setTicketTypeId(updatedTicket.getTicketTypeId());
                ticket.setEventId(updatedTicket.getEventId());
                ticket.setTicketSale(updatedTicket.getTicketSale());
                return ticketrepository.save(ticket);
            })
            .orElseGet(() -> {
                updatedTicket.setTicketId(ticketId);
                return ticketrepository.save(updatedTicket);
            });
    }

    // Delete ticket
    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable Long ticketId) {
        ticketrepository.deleteById(ticketId);
    }
}
