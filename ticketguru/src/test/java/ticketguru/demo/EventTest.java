import package ticketguru.demo.domain;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.Event;
 
class EventTest {
 
    @Test
    void testEventCreation() {
        LocalDate date = LocalDate.now();
 
        Event event = new Event(
                "Test Event",
                "Operaatalo",
                "Helsinki",
                date,
                "Jazz event",
                100
        );
 
        assertEquals("Test Event", event.getEventName());
        assertEquals("Operaatalo", event.getEventLocation());
        assertEquals("Helsinki", event.getEventCity());
        assertEquals(date, event.getEventDate());
        assertEquals("Jazz event", event.getEventDescription());
        assertEquals(100, event.getMaxNumberOfTickets());
    }
}
 