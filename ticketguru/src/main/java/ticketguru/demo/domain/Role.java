package ticketguru.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false)
    private Long roleId;

    @NotEmpty(message = "Role name is required")
    @Size(max = 50, message = "Role name must be at most 50 characters")
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Size(max = 255, message = "Notes must be at most 255 characters")
    @Column(name = "notes")
    private String notes;

    public Role() {}

    public Role(Long roleId, String roleName, String notes) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.notes = notes;
    }

    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + ", notes=" + notes + "]";
    }
}
