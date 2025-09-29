package ticketguru.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "roleId")  // Tämä on FK User-taulussa → Role-tauluun, Tämä tarkoittaa, että jokaisella käyttäjällä on yksi rooli.
    private Role role;

    public AppUser() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   public Role getRole() {
    return role;
    }

public void setRole(Role role) {
    this.role = role;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", firstname=").append(firstname);
        sb.append(", lastname=").append(lastname);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }

    
}
