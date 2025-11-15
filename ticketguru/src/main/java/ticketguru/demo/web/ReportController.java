package ticketguru.demo.web;

import java.math.BigDecimal;
import java.util.List;

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
        
        // 2. Laske kokonaismyynti (esimerkiksi)
        BigDecimal totalRevenue = sales.stream()
            .map(TicketSale::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Laske myytyjen lippujen kokonaismäärä
        long totalTicketsSold = sales.stream()
            .flatMap(sale -> sale.getTickets().stream())
            .count();

        // 4. Lähetä data Thymeleaf-näkymään
        model.addAttribute("sales", sales);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalTicketsSold", totalTicketsSold);

        return "salesreport"; // Vastaa tiedostoa: salesreport.html
    }
}