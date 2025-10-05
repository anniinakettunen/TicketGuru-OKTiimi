package ticketguru.demo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ticketguru.demo.domain.TicketType;
import ticketguru.demo.repositories.TicketTypeRepository;

@Controller
public class TicketTypeController {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeController(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @GetMapping("/tickettypes")
    public String listTicketTypes(Model model) {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        model.addAttribute("ticketTypes", ticketTypes);
        model.addAttribute("ticketType", new TicketType()); // lomaketta varten
        return "tickettypes";
    }

    @PostMapping("/savetickettype")
    public String saveTicketType(@ModelAttribute TicketType ticketType) {
        ticketTypeRepository.save(ticketType);
        return "redirect:/tickettypes";
    }

    @GetMapping("/edittickettype/{id}")
    public String editTicketType(@PathVariable("id") Long id, Model model) {
        TicketType ticketType = ticketTypeRepository.findById(id).orElse(new TicketType()); // korjattu
        model.addAttribute("ticketType", ticketType);
        model.addAttribute("ticketTypes", ticketTypeRepository.findAll());
        return "tickettypes";
    }

    @GetMapping("/deletetickettype/{id}")
    public String deleteTicketType(@PathVariable("id") Long id) {
        ticketTypeRepository.deleteById(id);
        return "redirect:/tickettypes";
    }

    
}
