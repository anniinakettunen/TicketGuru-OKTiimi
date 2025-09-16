package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.demo.domain.Event;
import ticketguru.demo.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    private EventRepository eventRepository;

    // GET: Hae kaikki eventit
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // GET: Hae event ID:n perusteella
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Luo uusi event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    // PUT: Päivitä event ID:n perusteella
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Event event = optionalEvent.get();
        event.setName(eventDetails.getName());
        event.setEventLocation(eventDetails.getEventLocation());
        event.setCity(eventDetails.getCity());
        event.setDate(eventDetails.getDate());
        event.setDescription(eventDetails.getDescription());
        event.setMaxNumberOfTickets(eventDetails.getMaxNumberOfTickets());
        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    // DELETE: Poista Event ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
