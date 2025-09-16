package ticketguru.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticketguru.demo.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByID(Long eventId);

    List<Event> findByName(String name);

    List<Event> findByEventLocation(String eventLocation);
}
