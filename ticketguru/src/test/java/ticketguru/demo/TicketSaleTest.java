package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TicketSaleTest {
@Test
void testGetSaleId() {
var one = new TicketSale(11111111111, "2.1.2026 14:00", 100.0, 11111111111, 11111111111);
assertEquals(11111111111, one.getSaleId());
}
@Test
void testGetDateTime() {
var two = new TicketSale(2222222222, "2.2.2026 15:00", 200.0, 2222222222, 2222222222);
assertEquals("2.2.2026 15:00", two.getDateTime());
}
@Test
void testGetPrice() {
var three = new TicketSale(3333333333, "2.3.2026 16:00", 300.0, 3333333333, 3333333333);
assertEquals(300.0, three.getPrice());
}
@Test
void testGetUser() {
var four = new TicketSale(4444444444, "2.4.2026 17:00", 400.0, 4444444444, 4444444444);
assertEquals(4444444444, four.getUser());
}
@Test
void testGetTickets() {
var five = new TicketSale(5555555555, "2.5.2026 18:00", 500.0, 5555555555, 5555555555);
assertEquals(5555555555, five.getTickets());
}
}
