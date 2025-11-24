package ticketguru.demo.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {

    @Test
    void testSettersAndGetters() {
        AppUser user = new AppUser();
        Role role = new Role(1L, "ADMIN", null);

        user.setId(1L);
        user.setUsername("joku");
        user.setPasswordHash("hashed");
        user.setFirstname("Joku");
        user.setLastname("Jokulainen");
        user.setEmail("joku@example.com");
        user.setPhone("12345678");
        user.setRole(role);

        assertEquals(1L, user.getId());
        assertEquals("joku", user.getUsername());
        assertEquals("hashed", user.getPasswordHash());
        assertEquals("Joku", user.getFirstname());
        assertEquals("Jokulainen", user.getLastname());
        assertEquals("joku@example.com", user.getEmail());
        assertEquals("12345678", user.getPhone());
        assertEquals(role, user.getRole());
    }
}
