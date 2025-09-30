package ticketguru.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;
    private Long ticketCode;

    @ManyToOne
    @JoinColumn(name="ticketTypeId", nullable=false)
    private TicketType ticketTypeId;

    @ManyToOne
    @JoinColumn(name="eventId", nullable=false)
    private Event eventId;

    @ManyToOne
    @JoinColumn(name="sale_id", nullable=true)
    private TicketSale ticketSale;

    public Ticket() {}

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(Long ticketCode) {
        this.ticketCode = ticketCode;
    }

    public TicketType getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(TicketType ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "Ticket [ticketId=" + ticketId + ", ticketCode=" + ticketCode + "]";
    }
}
