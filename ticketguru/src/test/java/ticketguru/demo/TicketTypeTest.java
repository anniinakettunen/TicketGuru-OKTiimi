package ticketguru.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ticketguru.demo.domain.TicketType;

public class TicketTypeTest {

    @Test
    public void testDefaultConstructor() {
        TicketType tt = new TicketType();
        assertNotNull(tt);
    }

    @Test
    public void testParameterizedConstructor() {
        TicketType tt = new TicketType("Adult", 10.0);
        assertEquals("Adult", tt.getTicketName());
        assertEquals(10.0, tt.getPrice());
    }

    @Test
    public void testSetters() {
        TicketType tt = new TicketType();

        tt.setTicketName("Child");
        tt.setPrice(5.0);

        assertEquals("Child", tt.getTicketName());
        assertEquals(5.0, tt.getPrice());
    }
}
