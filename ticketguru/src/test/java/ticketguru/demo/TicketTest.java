package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.Ticket;
public class TicketTest {
@Test
void testGetTicketId() {
var first = new Ticket(1, 1, 1, 1, 1);
assertEquals(11111111111, first.getTicketId());
}
@Test
void testGetTicketCode() {
var second = new Ticket(2, 2, 2, 2, 2);
assertEquals(2, second.getTicketCode());
}
@Test
void testGetTicketTypeId() {
var third = new Ticket(3, 3, 3, 3, 3);
assertEquals(3, third.getTicketTypeId());
}
@Test
void testGetEventId() {
var fourth = new Ticket(4, 4, 4, 4, 4);
assertEquals(4, fourth.getEventId());
}
@Test
void testGetTicketSale() {
var fifth = new Ticket(5, 5, 5, 5, 5);
assertEquals(5, fifth.getTicketSale());
}
}

