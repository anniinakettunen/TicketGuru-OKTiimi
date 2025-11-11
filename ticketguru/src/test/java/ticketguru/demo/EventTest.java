package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class EventTest {
@Test
void testGetEventId() {
var lightcarnival = new Event(11111111111, "lightcarnival", "Finland", "Helsinki", "1.9.2026", "Occasion in Linnanmäki at the evening where there are everywhere lights like christmas lights", 500);
assertEquals(11111111111, lightcarnival.getEventId());
}
@Test
void testGetEventName() {
var seadays = new Event(2222222222, "seadays", "Finland", "Kotka", "1.7.2026", "Event where there are seas as a theme", 1000);
assertEquals("seadays", seadays.getEventName());
}
@Test
void testGetEventLocation() {
var gamblingnight = new Event(3333333333, "gamblingnight", "USA", "Las Vegas", "1.2.2026", "Competition where there are expectionally high rewards in gambling", 1500);
assertEquals("USA", gamblingnight.getEventLocation());
}
@Test
void testGetEventCity() {
var movienight = new Event(4444444444, "movienight", "France", "Paris", "1.3.2026", "Event where you can watch movies by low price", 2000);
assertEquals("Paris", movienight.getEventCity());
}
@Test
void testGetEventDate() {
var climatemeeting = new Event(5555555555, "climatemeeting", "Sweden", "Stockholm", "1.10.2026", "Meeting where we decide of climate issues", 2500);
assertEquals("1.10.2026", climatemeeting.getEventDate());
}
@Test
void testGetEventDescription() {
var tractordays = new Event(6666666666, "tractordays", "Finland", "Salo", "1.8.2026", "Event where you can admire tractors", 3000);
assertEquals("Event where you can admire tractors", tractordays.getEventDescription());
}
@Test
void testGetMaxNumberOfTickets() {
var marathon = new Event(7777777777, "marathon", "Great Britain", "London", "1.6.2026", "London's marathon championship", 3500);
assertEquals(3500, marathon.getMaxNumberOfTickets);
}
}
