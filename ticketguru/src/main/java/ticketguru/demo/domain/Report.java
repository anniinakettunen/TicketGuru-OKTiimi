package ticketguru.demo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

    @Entity
    public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Long reportId;

        private String name;
        private String date;
        private String description;

        @ManyToOne
        @JoinColumn(name = "eventId", nullable = false)
        private Event eventId;

    public Report(Long reportId, String name, String date, String description, Event eventId) {
        this.reportId = reportId;
        this.name = name;
        this.date = date;
        this.description = description;
        this.eventId = eventId;
    }

    public Report() {
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportid(Long reportId) {
        this.reportId = reportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
    return this.reportId + this.name + this.date + this.description + this.eventId;
    }
    }

