package ticketguru.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ticketguru.demo.domain.Event;
import ticketguru.demo.repositories.EventRepository;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

   
    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "addevents"; // templates/addevents.html
    }

    
    @PostMapping("/save")
    public String saveEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/events/list";
    }

    @GetMapping("/list")
    public String showEvents(@RequestParam(required = false) String keyword, Model model) {
        List<Event> events;
        if (keyword != null && !keyword.isBlank()) {
            events = eventRepository.findByEventNameContainingIgnoreCaseOrEventCityContainingIgnoreCase(keyword, keyword);
        } else {
            events = eventRepository.findAll();
        }
        model.addAttribute("events", events);
        return "eventslist";
    }


    @GetMapping("/edit/{id}")
    public String showEditEventForm(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + id));
        model.addAttribute("event", event);
        return "editevent"; // templates/editevent.html
    }




    @GetMapping("/api")
    @ResponseBody
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

   
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return event.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Event event = optionalEvent.get();
        event.setEventName(eventDetails.getEventName());
        event.setEventLocation(eventDetails.getEventLocation());
        event.setEventCity(eventDetails.getEventCity());
        event.setEventDate(eventDetails.getEventDate());
        event.setEventDescription(eventDetails.getEventDescription());
        event.setMaxNumberOfTickets(eventDetails.getMaxNumberOfTickets());
        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }


    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(eventId);
        return ResponseEntity.noContent().build();
    }


@GetMapping("/delete/{eventId}")
public String showDeleteConfirmation(@PathVariable Long eventId, Model model) {
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + eventId));
    model.addAttribute("event", event);
    return "deleteevent"; // templates/deleteevent.html
}


@PostMapping("/delete/{eventId}")
public String deleteEventHtml(@PathVariable Long eventId) {
    eventRepository.deleteById(eventId);
    return "redirect:/events/list";
}


    @PostMapping("/update/{eventId}")
    public String updateEventForm(@PathVariable Long eventId, @ModelAttribute Event eventDetails) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + eventId));
        event.setEventName(eventDetails.getEventName());
        event.setEventLocation(eventDetails.getEventLocation());
        event.setEventCity(eventDetails.getEventCity());
        event.setEventDate(eventDetails.getEventDate());
        event.setEventDescription(eventDetails.getEventDescription());
        event.setMaxNumberOfTickets(eventDetails.getMaxNumberOfTickets());
        eventRepository.save(event);
        return "redirect:/events/list";
    }

}
