package ticketguru.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class TicketSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleId;

    @NotNull(message = "Date and time are required")
    private LocalDateTime dateTime;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or positive")
    @DecimalMax(value = "999999.99", message = "Price cannot exceed €999,999.99")
    @Digits(integer = 6, fraction = 2, message = "Price must have at most 6 digits before decimal and 2 after")
    private BigDecimal price;

    @ManyToOne // Yhdellä käyttäjällä voi olla useita TicketSale-tapahtumia
    @NotNull(message = "User is required")
    @JoinColumn(name = "user_id", nullable = false) 
    private AppUser user; 

    @OneToMany(mappedBy = "ticketSale")  // Yksi TicketSale voi sisältää useita lippuja
    @JsonManagedReference
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
