package ticketguru.demo.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Table(name = "event",
       uniqueConstraints = @UniqueConstraint(columnNames = {"event_name", "event_date"}))
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @NotBlank(message = "Event name is required")
    @Size(min = 2, max = 100, message = "Event name must be between 2 and 100 characters")
    private String eventName;

    @NotBlank(message = "Event location is required")
    private String eventLocation;

    @NotBlank(message = "Event city is required")
    private String eventCity;

    @NotNull(message = "Event date is required")
    @FutureOrPresent(message = "Event date must be today or in the future")
    private LocalDate eventDate;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String eventDescription;

    @NotNull(message = "Maximum number of tickets is required")
    @Min(value = 1, message = "There must be at least 1 ticket")
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
