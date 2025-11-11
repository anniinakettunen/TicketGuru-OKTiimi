package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TicketTypeTest {
@Test
void testGetTicketTypeId() {
var childsticket = new TicketType(11111111111, "childsticket", 5.0);
assertEquals(11111111111, childsticket.getTicketTypeId());
}
@Test
void testGetTicketName() { 
var adultsticket = new TicketType(2222222222, "adultsticket", 10.0);
assertEquals("adultsticket", adultsticket.getTicketName());
}
@Test
void testGetPrice() {
var pensionersticket = new TicketType(3333333333, "pensionersticket", 15.0);
assertEquals(15.0, pensionersticket.getPrice());
}
}
