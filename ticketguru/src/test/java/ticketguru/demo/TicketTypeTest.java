package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.TicketType;
public class TicketTypeTest {
@Test
void testGetTicketTypeId() {
var childsticket = new TicketType(1, "childsticket", 5.0);
assertEquals(1, childsticket.getTicketTypeId());
}
@Test
void testGetTicketName() { 
var adultsticket = new TicketType(2, "adultsticket", 10.0);
assertEquals("adultsticket", adultsticket.getTicketName());
}
@Test
void testGetPrice() {
var pensionersticket = new TicketType(3, "pensionersticket", 15.0);
assertEquals(15.0, pensionersticket.getPrice());
}
}
