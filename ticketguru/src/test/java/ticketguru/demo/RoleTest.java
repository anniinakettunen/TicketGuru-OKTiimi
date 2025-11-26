package ticketguru.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ticketguru.demo.domain.Role;

public class RoleTest {

    private Role designer;

    @BeforeEach
    void setUp() {
        designer = new Role(1L, "designer", "makes instructions for actions in the project");
    }

    @Test
    void getRoleIdIsPresentAndReturnsTheRoleId() {
        assertEquals(1L, designer.getRoleId());
    }

    @Test
    void getRoleNameIsPresentAndReturnsTheRoleName() {
        assertEquals("designer", designer.getRoleName());
    }

    @Test
    void getNotesIsPresentAndReturnsTheNotes() {
        assertEquals("makes instructions for actions in the project", designer.getNotes());
    }
}
