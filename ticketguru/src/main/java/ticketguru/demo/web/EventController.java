package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ticketguru.demo.domain.Event;
import ticketguru.demo.repositories.EventRepository;

@Controller
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    
    @GetMapping("/events/new")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "addevents";
    }
    
    @PostMapping("/events/save")
    public String saveEvent(@ModelAttribute Event event, RedirectAttributes redirectAttributes) {
        eventRepository.save(event);
        redirectAttributes.addFlashAttribute("message", "Event saved successfully!");
        return "redirect:/";
    }
}
