package ticketguru.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.repositories.TicketSaleRepository;

import java.util.List;

@Service
public class TicketsToEmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TicketSaleRepository ticketSaleRepository;
    
    public void sendTicketEmail(TicketSale ticketSale, String customerEmail) {
        try {
            // Reload the ticket sale with tickets from database to ensure all data is available
            TicketSale reloadedSale = ticketSaleRepository.findById(ticketSale.getSaleId())
                .orElse(ticketSale);
            
            System.out.println("Sending email to: " + customerEmail);
            System.out.println("Number of tickets: " + (reloadedSale.getTickets() != null ? reloadedSale.getTickets().size() : 0));
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("eventhubdemoemail@gmail.com");
            message.setTo(customerEmail);
            message.setSubject("Your Ticket Codes - TicketGuru");
            message.setText(buildEmailContent(reloadedSale));
            
            mailSender.send(message);
            System.out.println("Email sent successfully to: " + customerEmail);
        } catch (Exception e) {
            // Log the error but don't fail the sale
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private String buildEmailContent(TicketSale ticketSale) {
        StringBuilder content = new StringBuilder();
        content.append("Dear Customer,\n\n");
        content.append("Thank you for your purchase!\n\n");
        content.append("Your ticket codes:\n\n");
        
        List<Ticket> tickets = ticketSale.getTickets();
        
        if (tickets == null || tickets.isEmpty()) {
            content.append("No tickets found.\n\n");
        } else {
            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                content.append(String.format("%d. Ticket Code: %s\n", 
                    i + 1, 
                    ticket.getTicketCode()));
                content.append(String.format("   Event: %s\n", 
                    ticket.getEventId() != null ? ticket.getEventId().getEventName() : "N/A"));
                content.append(String.format("   Type: %s\n\n", 
                    ticket.getTicketTypeId() != null ? ticket.getTicketTypeId().getTicketName() : "N/A"));
            }
        }
        
        content.append("Please keep these ticket codes safe.\n");
        content.append("Show them at the event entrance.\n\n");
        content.append("Best regards,\n");
        content.append("TicketGuru Team");
        
        return content.toString();
    }
}
