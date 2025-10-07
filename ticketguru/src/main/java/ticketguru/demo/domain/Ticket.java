package ticketguru.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    private Long ticketCode; // Optional, can be generated if missing

    @ManyToOne
    @JoinColumn(name = "ticketTypeId", nullable = false)
    @NotNull(message = "TicketType must not be null")
    private TicketType ticketTypeId;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    @NotNull(message = "Event must not be null")
    private Event eventId;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = true)
    @JsonBackReference
    private TicketSale ticketSale;

    public Ticket() {}

    // ===== Getters and Setters =====
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

    public TicketSale getTicketSale() {
        return ticketSale;
    }

    public void setTicketSale(TicketSale ticketSale) {
        this.ticketSale = ticketSale;
    }

    @Override
    public String toString() {
        return "Ticket [ticketId=" + ticketId + ", ticketCode=" + ticketCode + "]";
    }
}
