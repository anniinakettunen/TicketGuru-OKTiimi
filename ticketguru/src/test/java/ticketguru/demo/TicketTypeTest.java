package ticketguru.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.TicketType;

public class TicketTypeTest {

    private TicketType childsticket;

    @BeforeEach
    void setUp() {
        childsticket = new TicketType("childsticket", 5.0);
    }

    @Test
    void getTicketTypeIdIsPresentAndReturnsTheTicketTypeId() {
        assertEquals(null, childsticket.getTicketTypeId());
    }

    @Test
    void getTicketNameIsPresentAndReturnsTheTicketName() {
        assertEquals("childsticket", childsticket.getTicketName());
    }

    @Test
    void getPriceIsPresentAndReturnsThePrice() {
        assertEquals(5.0, childsticket.getPrice());
    }
}
