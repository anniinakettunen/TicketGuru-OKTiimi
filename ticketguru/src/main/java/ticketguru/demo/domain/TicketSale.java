package ticketguru.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TicketSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleId;
    private LocalDateTime dateTime;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private AppUser Id;

    public TicketSale() {
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

    public AppUser getId() {
        return Id;
    }

    public void setUserId(AppUser Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "TicketSale{" +
                "saleId=" + saleId +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", userId=" + Id +
                '}';
    }
}