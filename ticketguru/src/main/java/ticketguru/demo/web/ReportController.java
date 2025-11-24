package ticketguru.demo.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.repositories.TicketSaleRepository;

@Controller
public class ReportController {

    @Autowired
    private TicketSaleRepository ticketSaleRepository;

    @GetMapping("/salesreport")
public String getSalesReport(Model model) {
    
    // 1. Hae kaikki myyntitapahtumat
    List<TicketSale> sales = ticketSaleRepository.findAll();
    
    // 2. Kokonaistulot
    BigDecimal totalRevenue = sales.stream()
    .map(TicketSale::getPrice)
    .reduce(BigDecimal.ZERO, BigDecimal::add);


    // 3. Kokonaislippumäärä
    long totalTicketsSold = sales.stream()
    .mapToLong(sale -> sale.getTickets().size())
    .sum();

    BigDecimal oskariTotal = sales.stream()
    .filter(sale -> "oskari".equals(sale.getUser().getUsername()))
    .map(TicketSale::getPrice)
    .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal jaskaTotal = sales.stream()
    .filter(sale -> "jaska".equals(sale.getUser().getUsername()))
    .map(TicketSale::getPrice)
    .reduce(BigDecimal.ZERO, BigDecimal::add);

    model.addAttribute("oskariTotal", oskariTotal);
    model.addAttribute("jaskaTotal", jaskaTotal);


    // 4. Tapahtumakohtainen myynti
    Map<Long, BigDecimal> eventRevenue = new HashMap<>();
    for (TicketSale sale : sales) {
        if (!sale.getTickets().isEmpty()) {
            Long eventId = sale.getTickets().get(0).getEventId().getEventId();
            eventRevenue.put(eventId,
                eventRevenue.getOrDefault(eventId, BigDecimal.ZERO).add(sale.getPrice()));
        }
    }

    // 5. Lähetä data Thymeleaf-näkymään
    model.addAttribute("sales", sales);
    model.addAttribute("totalRevenue", totalRevenue);
    model.addAttribute("totalTicketsSold", totalTicketsSold);
    model.addAttribute("eventRevenue", eventRevenue);

    return "salesreport";
}
}