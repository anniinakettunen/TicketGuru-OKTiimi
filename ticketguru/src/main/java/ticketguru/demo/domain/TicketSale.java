package ticketguru.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
public class TicketSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleId;
    private LocalDateTime dateTime;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;

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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TicketSale{" +
                "saleId=" + saleId +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}