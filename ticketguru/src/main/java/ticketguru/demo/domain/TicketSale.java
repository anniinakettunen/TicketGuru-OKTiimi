package ticketguru.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TicketSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleId;

    private LocalDateTime dateTime;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private AppUser user; 

    @OneToMany
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticketId;

    public TicketSale() {}


    public Long getSaleId() {      
        return saleId;
    }

    public void setSaleId(Long saleId) { 
        this.saleId = saleId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Ticket getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
    }


    @Override
    public String toString() {
        return "TicketSale{" +
                "saleId=" + saleId +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
