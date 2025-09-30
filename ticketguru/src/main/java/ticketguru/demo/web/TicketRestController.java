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

@GetMapping
public List<Ticket> getAllTickets() {
return ticketrepository.findAll();
}

@GetMapping("/{ticketId}")
public Optional<Ticket> getTicketByTicketId(@PathVariable Long ticketId) {
return ticketrepository.findByTicketId(ticketId);
}

@PostMapping
public Ticket createTicket(@RequestBody Ticket ticket) {
return ticketrepository.save(ticket);
}

@PutMapping("/{ticketId}")
public Ticket updateTicket(@PathVariable Long ticketId, @RequestBody Ticket updatedTicket) {
return ticketrepository.findByTicketId(ticketId)
.map(ticket -> {
ticket.setTicketCode(updatedTicket.getTicketCode());
return ticketrepository.save(ticket);
})
.orElseGet(() -> {
updatedTicket.setTicketId(ticketId);
return ticketrepository.save(updatedTicket);
});
}

@DeleteMapping("/{ticketId}")
public void deleteTicket(@PathVariable Long ticketId) {
ticketrepository.deleteByTicketId(ticketId);
}

}
