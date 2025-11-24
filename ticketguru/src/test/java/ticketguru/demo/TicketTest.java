package ticketguru.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketType;
import ticketguru.demo.domain.Event;

public class TicketTest {

    @Test
    public void testDefaultConstructor() {
        Ticket ticket = new Ticket();
        assertNotNull(ticket);
        assertFalse(ticket.isUsed());
    }

    @Test
    public void testUsedConstructor() {
        Ticket ticket = new Ticket(true);
        assertTrue(ticket.isUsed());
    }

    @Test
    public void testSettersAndGetters() {
        Ticket ticket = new Ticket();

        ticket.setTicketCode(123L);
        assertEquals(123L, ticket.getTicketCode());

        TicketType type = new TicketType("Adult", 10.0);
        ticket.setTicketTypeId(type);
        assertEquals(type, ticket.getTicketTypeId());

        Event event = new Event();
        ticket.setEventId(event);
        assertEquals(event, ticket.getEventId());

        ticket.setUsed(true);
        assertTrue(ticket.isUsed());
    }
}
