package ticketguru.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ticketguru.demo.domain.Event;
import ticketguru.demo.repositories.EventRepository;
import ticketguru.demo.repositories.TicketRepository;

@SpringBootTest
@ActiveProfiles("dev")
public class LippuIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void cleanDatabase() {
        ticketRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    void testCreateAndFindEvent() {
        Event event = new Event(
            "Integration Test Concert",
            "Helsinki Arena",
            "Helsinki",
            LocalDate.now().plusDays(5),
            "Integration test event for TicketGuru",
            1000
        );

        // tieto tallennetaan tietokantaan
        Event savedEvent = eventRepository.save(event);

        // varmistetaan, että eventID on luotu
        assertThat(savedEvent.getEventId()).isNotNull();

        // haetaan tapahtuma nimellä
        List<Event> foundByName = eventRepository.findByEventName("Integration Test Concert");

        // varmistetaan, että tapahtuma löytyy ja tiedot vastaavat
        assertThat(foundByName).isNotEmpty();
        Event found = foundByName.get(0);

        assertThat(found.getEventCity()).isEqualTo("Helsinki");
        assertThat(found.getEventLocation()).isEqualTo("Helsinki Arena");
        assertThat(found.getMaxNumberOfTickets()).isEqualTo(1000);
        assertThat(found.getEventDescription()).contains("TicketGuru");
    }
}
