package ticketguru.demo;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.TicketSale;
public class TicketSaleTest {
public final TicketSale one = new TicketSale(1, "2.1.2026 14:00", 100.0, 1, 1);
@Test
void getSaleIdIsPresentAndReturnsTheSaleId() throws Exception {
checkMethod(one, "getSaleId", 1);
}
@Test
void getDateTimeIsPresentAndReturnsTheDateTime() throws Exception {
checkMethod(one, "getDateTime", "2.1.2026 14:00");
}
@Test
void getPriceIsPresentAndReturnsThePrice() throws Exception {
checkMethod(one, "getPrice", 100.0);
}
@Test
void getUserIsPresentAndReturnsTheUser() throws Exception {
checkMethod(one, "getUser", 1);
}
@Test
void getTicketsIsPresentAndReturnsTheTickets() throws Exception {
checkMethod(one, "getTickets", 1);
}
}
