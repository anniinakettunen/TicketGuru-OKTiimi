package ticketguru.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.Event;
import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.domain.TicketType;

public class TicketTest {

    private Ticket first;
    private TicketType testTicketType;
    private Event testEvent;
    private TicketSale testTicketSale;

    @BeforeEach
    void setUp() {
        testTicketType = new TicketType();
        testTicketType.setTicketName("Standard");
        testTicketType.setPrice(10.0);

        testEvent = new Event();
        testEvent.setId(1L);
        testEvent.setEventName("Test Event");
        testEvent.setEventLocation("Test Location");
        testEvent.setEventCity("Test City");
        testEvent.setEventDate(LocalDate.of(2026, 1, 1));
        testEvent.setEventDescription("Test Description");
        testEvent.setMaxNumberOfTickets(100);

        testTicketSale = new TicketSale();
        testTicketSale.setSaleId(1L);

        first = new Ticket();
        first.setTicketId(1L);
        first.setTicketCode(1L);
        first.setTicketTypeId(testTicketType);
        first.setEventId(testEvent);
        first.setTicketSale(testTicketSale);
    }

    @Test
    void getTicketIdIsPresentAndReturnsTheTicketId() {
        assertEquals(1L, first.getTicketId());
    }

    @Test
    void getTicketCodeIsPresentAndReturnsTheTicketCode() {
        assertEquals(1L, first.getTicketCode());
    }

    @Test
    void getTicketTypeIdIsPresentAndReturnsTheTicketTypeId() {
        assertEquals(testTicketType, first.getTicketTypeId());
    }

    @Test
    void getEventIdIsPresentAndReturnsTheEventId() {
        assertEquals(testEvent, first.getEventId());
    }

    @Test
    void getTicketSaleIsPresentAndReturnsTheTicketSale() {
        assertEquals(testTicketSale, first.getTicketSale());
    }
}
