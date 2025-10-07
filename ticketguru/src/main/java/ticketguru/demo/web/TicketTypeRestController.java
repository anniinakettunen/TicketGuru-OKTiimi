package ticketguru.demo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ticketguru.demo.domain.TicketType;
import ticketguru.demo.repositories.TicketTypeRepository;

@RestController
@RequestMapping("/api/tickettypes")
@Validated
public class TicketTypeRestController {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeRestController(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    //GET: Get all ticket types
    @GetMapping
    public List<TicketType> getAll() {
        return ticketTypeRepository.findAll();
    }

    // GET: Get ticket type by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketType> getById(@PathVariable Long id) {
        return ticketTypeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create new ticket type
    @PostMapping
    public ResponseEntity<TicketType> create(@Valid @RequestBody TicketType ticketType) {
        TicketType saved = ticketTypeRepository.save(ticketType);
        return ResponseEntity.ok(saved);
    }

    // PUT : Update ticket type
    @PutMapping("/{id}")
    public ResponseEntity<TicketType> update(@PathVariable Long id, @Valid @RequestBody TicketType updatedType) {
        return ticketTypeRepository.findById(id)
                .map(existing -> {
                    existing.setTicketName(updatedType.getTicketName());
                    existing.setPrice(updatedType.getPrice());
                    ticketTypeRepository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Delete ticket type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticketTypeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
