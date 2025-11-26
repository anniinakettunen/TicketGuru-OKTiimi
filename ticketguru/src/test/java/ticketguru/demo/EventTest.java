package ticketguru.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.Event;

public class EventTest {

    private Event lightcarnival;

    @BeforeEach
    void setUp() {
        lightcarnival = new Event();
        lightcarnival.setId(1L);
        lightcarnival.setEventName("lightcarnival");
        lightcarnival.setEventLocation("Finland");
        lightcarnival.setEventCity("Helsinki");
        lightcarnival.setEventDate(LocalDate.of(2026, 9, 1));
        lightcarnival.setEventDescription("Occasion in Linnanmäki at the evening where there are everywhere lights like christmas lights");
        lightcarnival.setMaxNumberOfTickets(500);
    }

    @Test
    void getEventIdIsPresentAndReturnsTheEventId() {
        assertEquals(1L, lightcarnival.getEventId());
    }

    @Test
    void getEventNameIsPresentAndReturnsTheEventName() {
        assertEquals("lightcarnival", lightcarnival.getEventName());
    }

    @Test
    void getEventLocationIsPresentAndReturnsTheEventLocation() {
        assertEquals("Finland", lightcarnival.getEventLocation());
    }

    @Test
    void getEventCityIsPresentAndReturnsTheEventCity() {
        assertEquals("Helsinki", lightcarnival.getEventCity());
    }

    @Test
    void getEventDateIsPresentAndReturnsTheEventDate() {
        assertEquals(LocalDate.of(2026, 9, 1), lightcarnival.getEventDate());
    }

    @Test
    void getEventDescriptionIsPresentAndReturnsTheEventDescription() {
        assertEquals("Occasion in Linnanmäki at the evening where there are everywhere lights like christmas lights", lightcarnival.getEventDescription());
    }

    @Test
    void getMaxNumberOfTicketsIsPresentAndReturnsTheMaxNumberOfTickets() {
        assertEquals(500, lightcarnival.getMaxNumberOfTickets());
    }
}
