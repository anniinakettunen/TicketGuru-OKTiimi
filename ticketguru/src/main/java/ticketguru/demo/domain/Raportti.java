package ticketguru.demo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Raportti {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private String id;
private String nimi;
private String paivays;
private String kuvaus;
private String tapahtumaid;
public Raportti(String id, String nimi, String paivays, String kuvaus, String tapahtumaid) {
this.id = id;
this.nimi = nimi;
this.paivays = paivays;
this.kuvaus = kuvaus;
this.tapahtumaid = tapahtumaid;
}
public String getId() {
return id;
}
public void setId(String id) {
this.id = id;
}
public String getNimi() {
return nimi;
}
public void setNimi(String nimi) {
this.nimi = nimi;
}
public String getPaivays() {
return paivays;
}
public void setPaivays(String paivays) {
this.paivays = paivays;
}
public String getKuvaus() {
return kuvaus;
}
public void setKuvaus(String kuvaus) {
this.kuvaus = kuvaus;
}
public String getTapahtumaid() {
return tapahtumaid;
}
public void setTapahtumaid(String tapahtumaid) {
this.tapahtumaid = tapahtumaid;
}
@Override
public String toString() {
return this.id + this.nimi + this.paivays + this.kuvaus + this.tapahtumaid;
}
}
