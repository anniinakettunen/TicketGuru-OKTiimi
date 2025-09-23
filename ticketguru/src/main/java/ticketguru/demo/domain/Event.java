package ticketguru.demo.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String eventName;
    private String eventLocation;
    private String eventCity;
    private LocalDate eventDate;
    private String eventDescription;
    private Integer maxNumberOfTickets;

    public Event() {}

    public Event(String eventName, String eventLocation, String eventCity, LocalDate eventDate, String eventDescription, Integer maxNumberOfTickets) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventCity = eventCity;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.maxNumberOfTickets = maxNumberOfTickets;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventCity() {
        return eventCity;
    }

    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Integer getMaxNumberOfTickets() {
        return maxNumberOfTickets;
    }

    public void setMaxNumberOfTickets(Integer maxNumberOfTickets) {
        this.maxNumberOfTickets = maxNumberOfTickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + eventId +
                ", name='" + eventName + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", city='" + eventCity + '\'' +
                ", date=" + eventDate +
                ", description='" + eventDescription + '\'' +
                ", maxNumberOfTickets=" + maxNumberOfTickets +
                '}';
    }
}
