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
private String eventid;
public Raportti(String reportid, String name, String date, String description, String eventid) {
this.eventid = eventid;
this.name = name;
this.date = date;
this.description = description;
this.eventid = eventid;
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
public String getEventid() {
return eventid;
}
public void setEventid(String eventid) {
this.eventid = eventid;
}
@Override
public String toString() {
return this.reportid + this.name + this.date + this.description + this.eventid;
}
}

