package ticketguru.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.Event;
public class EventTest {
public final Event lightcarnival = new Event(1, "lightcarnival", "Finland", "Helsinki", "1.9.2026", "Occasion in Linnanmäki at the evening where there are everywhere lights like christmas lights", 500);
@Test
void getEventIdIsPresentAndReturnsTheEventId() throws Exception {
checkMethod(lightcarnival, "getEventId", 1);
}
@Test
void getEventNameIsPresentAndReturnsTheEventName() throws Exception {
checkMethod(lightcarnival, "getEventName", "lightcarnival");
}
@Test
void getEventLocationIsPresentAndReturnsTheEventLocation() throws Exception {
checkMethod(lightcarnival, "getEventLocation", "Finland");
}
@Test
void getEventCityIsPresentAndReturnsTheEventCity() throws Exception {
checkMethod(lightcarnival, "getEventCity", "Helsinki");
}
@Test
void getEventDateIsPresentAndReturnsTheEventDate() throws Exception {
checkMethod(lightcarnival, "getEventDate", "1.9.2026");
}
@Test
void getEventDescriptionIsPresentAndReturnsTheEventDescription() throws Exception {
checkMethod(lightcarnival, "getEventDescription", "Occasion in Linnanmäki at the evening where there are everywhere lights like christmas lights");
}
@Test
void getMaxNumberOfTicketsIsPresentAndReturnsTheMaxNumberOfTickets() throws Exception {
checkMethod(lightcarnival, "getMaxNumberOfTickets", 500);
}
}
 