package ticketguru.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne // Yhdellä käyttäjällä voi olla useita TicketSale-tapahtumia
    @JoinColumn(name = "user_id", nullable = false) 
    private AppUser user; 

    @OneToMany(mappedBy = "ticketSale")  // Yksi TicketSale voi sisältää useita lippuja
    private List<Ticket> tickets;

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
