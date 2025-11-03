package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ticketguru.demo.repositories.TicketRepository;

@Controller
@RequestMapping("/tickets")
public class TicketCheckController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/check")
    public String showTicketCheckPage(@RequestParam(name = "ticketCode", required = false) Long ticketCode,
                                      Model model) {
        if (ticketCode != null) {
            ticketRepository.findByTicketCode(ticketCode)
                    .ifPresent(ticket -> model.addAttribute("ticket", ticket));
        }
        return "ticketcheck"; // looks for src/main/resources/templates/ticketcheck.html
    }
}
