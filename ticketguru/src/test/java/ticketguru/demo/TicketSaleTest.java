package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TicketSaleTest {
@Test
void testGetSaleId() {
var one = new TicketSale(1, "2.1.2026 14:00", 100.0, 1, 1);
assertEquals(1, one.getSaleId());
}
@Test
void testGetDateTime() {
var two = new TicketSale(2, "2.2.2026 15:00", 200.0, 2, 2);
assertEquals("2.2.2026 15:00", two.getDateTime());
}
@Test
void testGetPrice() {
var three = new TicketSale(3, "2.3.2026 16:00", 300.0, 3, 3);
assertEquals(300.0, three.getPrice());
}
@Test
void testGetUser() {
var four = new TicketSale(4, "2.4.2026 17:00", 400.0, 4, 4);
assertEquals(4, four.getUser());
}
@Test
void testGetTickets() {
var five = new TicketSale(5, "2.5.2026 18:00", 500.0, 5, 5);
assertEquals(5, five.getTickets());
}
}

