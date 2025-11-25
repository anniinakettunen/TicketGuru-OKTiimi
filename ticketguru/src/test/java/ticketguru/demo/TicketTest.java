package ticketguru.demo;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.Ticket;
public class TicketTest {
public final Ticket first = new Ticket(1, 1, 1, 1, 1);
@Test
void getTicketIdIsPresentAndReturnsTheTicketId() throws Exception {
checkMethod(first, "getTicketId", 1);
}
@Test
void getTicketCodeIsPresentAndReturnsTheTicketCode() throws Exception {
checkMethod(first, "getTicketCode", 1);
}
@Test
void getTicketTypeIdIsPresentAndReturnsTheTicketTypeId() throws Exception {
checkMethod(first, "getTicketTypeId", 1);
}
@Test
void getEventIdIsPresentAndReturnsTheEventId() throws Exception {
checkMethod(first, "getEventId", 1);
}
@Test
void getTicketSaleIsPresentAndReturnsTheTicketSale() throws Exception {
checkMethod(first, "getTicketSale", 1);
}
}
