package ticketguru.demo;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.TicketType;
public class TicketTypeTest {
public final TicketType childsticket = new TicketType(1, "childsticket", 5.0);
@Test
void getTicketTypeIdIsPresentAndReturnsTheTicketTypeId() throws Exception {
checkMethod(childsticket, "getTicketTypeId", 1);
}
@Test
void getTicketNameIsPresentAndReturnsTheTicketName() throws Exception {
checkMethod(childsticket, "getTicketName", "childsticket");
}
@Test
void getPriceIsPresentAndReturnsThePrice() throws Exception {
checkMethod(childsticket, "getPrice", 5.0);
}
}
