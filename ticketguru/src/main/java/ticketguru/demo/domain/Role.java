package ticketguru.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RoleId;

    private String RoleName;
    private String Notes;

    // Parametrillinen konstruktori
    public Role(Long RoleId, String RoleName, String Notes) {
        this.RoleId = RoleId;
        this.RoleName = RoleName;
        this.Notes = Notes;
    }

    // Oletuskonstruktori
    public Role() {}

    // Getterit ja setterit
    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        this.RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        this.RoleName = roleName;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        this.Notes = notes;
    }

    // toString-metodi
    @Override
    public String toString() {
        return "Role [RoleId=" + RoleId + ", RoleName=" + RoleName + ", Notes=" + Notes + "]";
    }
}
