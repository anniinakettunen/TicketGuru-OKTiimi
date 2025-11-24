package ticketguru.demo.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRole() {
        Role role = new Role(1L, "ADMIN", "Administrator role");

        assertEquals(1L, role.getRoleId());
        assertEquals("ADMIN", role.getRoleName());
        assertEquals("Administrator role", role.getNotes());
    }
}
