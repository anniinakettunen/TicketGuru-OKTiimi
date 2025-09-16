package ticketguru.demo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private String reportid;
        private String name;
        private String date;
        private String description;
        private Event eventId;

    public Report(String reportid, String name, String date, String description, Event eventId) {
        this.reportid = reportid;
        this.name = name;
        this.date = date;
        this.description = description;
        this.eventId = eventId;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
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

    public Event getEventid() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
    return this.reportid + this.name + this.date + this.description + this.eventId;
    }
    }

