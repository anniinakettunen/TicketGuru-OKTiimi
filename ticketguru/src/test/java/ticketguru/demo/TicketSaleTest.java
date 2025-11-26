package ticketguru.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.TicketSale;

public class TicketSaleTest {

    private TicketSale one;
    private AppUser testUser;

    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        
        one = new TicketSale();
        one.setSaleId(1L);
        one.setDateTime(LocalDateTime.of(2026, 1, 2, 14, 0));
        one.setPrice(new BigDecimal("100.0"));
        one.setUser(testUser);
        one.setTickets(new ArrayList<>());
    }

    @Test
    void getSaleIdIsPresentAndReturnsTheSaleId() {
        assertEquals(1L, one.getSaleId());
    }

    @Test
    void getDateTimeIsPresentAndReturnsTheDateTime() {
        assertEquals(LocalDateTime.of(2026, 1, 2, 14, 0), one.getDateTime());
    }

    @Test
    void getPriceIsPresentAndReturnsThePrice() {
        assertEquals(new BigDecimal("100.0"), one.getPrice());
    }

    @Test
    void getUserIsPresentAndReturnsTheUser() {
        assertEquals(testUser, one.getUser());
    }

    @Test
    void getTicketsIsPresentAndReturnsTheTickets() {
        assertEquals(new ArrayList<>(), one.getTickets());
    }
}
