package ticketguru.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.TicketSale;

public class TicketSaleTest {

    @Test
    public void testDefaultConstructor() {
        TicketSale sale = new TicketSale();
        assertNotNull(sale);
    }

    @Test
    public void testSettersAndGetters() {
        TicketSale sale = new TicketSale();

        LocalDateTime now = LocalDateTime.now();
        sale.setDateTime(now);
        assertEquals(now, sale.getDateTime());

        BigDecimal price = new BigDecimal("29.99");
        sale.setPrice(price);
        assertEquals(price, sale.getPrice());

        AppUser user = new AppUser();
        sale.setUser(user);
        assertEquals(user, sale.getUser());
    }
}
