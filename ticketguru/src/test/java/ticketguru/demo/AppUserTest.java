package ticketguru.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.Role;

public class AppUserTest {
    
    private AppUser donaldtrump;
    private Role testRole;
    
    @BeforeEach
    void setUp() {
        testRole = new Role();
        testRole.setRoleId(1L);
        testRole.setRoleName("USER");
        testRole.setNotes("Test role");
        
        donaldtrump = new AppUser();
        donaldtrump.setId(1L);
        donaldtrump.setUsername("donaldtrump");
        donaldtrump.setPasswordHash("Abc12345");
        donaldtrump.setFirstname("Donald");
        donaldtrump.setLastname("Trump");
        donaldtrump.setEmail("donald.trump@gmail.com");
        donaldtrump.setPhone("+358406285791");
        donaldtrump.setRole(testRole);
    }
    
    @Test
    void getIdIsPresentAndReturnsTheId() {
        assertEquals(1L, donaldtrump.getId());
    }
    
    @Test
    void getUsernameIsPresentAndReturnsTheUsername() {
        assertEquals("donaldtrump", donaldtrump.getUsername());
    }
    
    @Test
    void getPasswordHashIsPresentAndReturnsThePasswordHash() {
        assertEquals("Abc12345", donaldtrump.getPasswordHash());
    }
    
    @Test
    void getFirstnameIsPresentAndReturnsTheFirstname() {
        assertEquals("Donald", donaldtrump.getFirstname());
    }
    
    @Test
    void getLastnameIsPresentAndReturnsTheLastname() {
        assertEquals("Trump", donaldtrump.getLastname());
    }
    
    @Test
    void getEmailIsPresentAndReturnsTheEmail() {
        assertEquals("donald.trump@gmail.com", donaldtrump.getEmail());
    }
    
    @Test
    void getPhoneIsPresentAndReturnsThePhone() {
        assertEquals("+358406285791", donaldtrump.getPhone());
    }
    
    @Test
    void getRoleIsPresentAndReturnsTheRole() {
        assertEquals(testRole, donaldtrump.getRole());
    }
}
