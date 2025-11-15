
package ticketguru.demo;
import ticketguru.demo.domain.Ticket;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TicketTest {
@Test
void testGetTicketId() {
var first = new Ticket(11111111111, 111111111111, 11111111111, 11111111111, 11111111111);
assertEquals(11111111111, first.getTicketId());
}
@Test
void testGetTicketCode() {
var second = new Ticket(2222222222, 22222222222, 2222222222, 2222222222, 2222222222);
assertEquals(22222222222, second.getTicketCode());
}
@Test
void testGetTicketTypeId() {
var third = new Ticket(3333333333, 33333333333, 3333333333, 3333333333, 3333333333);
assertEquals(3333333333, third.getTicketTypeId());
}
@Test
void testGetEventId() {
var fourth = new Ticket(4444444444, 44444444444, 4444444444, 4444444444, 4444444444);
assertEquals(4444444444, fourth.getEventId());
}
@Test
void testGetTicketSale() {
var fifth = new Ticket(5555555555, 55555555555, 5555555555, 5555555555, 5555555555);
assertEquals(5555555555, fifth.getTicketSale());
}
}
