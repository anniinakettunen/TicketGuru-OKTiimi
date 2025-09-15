package ticketguru.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class TicketController {

    @GetMapping("/")  
    public String home() {
        return "Hello, TicketGuru is running!";
    }

}