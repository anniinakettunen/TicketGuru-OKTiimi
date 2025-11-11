package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class RoleTest {
@Test
void testGetRoleId() {
var designer = new Role(11111111111, "designer", "makes instructions for actions in the project");
assertEquals(11111111111, designer.getRoleId());
}
@Test
void testGetRoleName() {
var hardworker = new Role(2222222222, "hardworker", "responsibility of implementation in the project");
assertEquals("hardworker", hardworker.getRoleName());
}
@Test
void testGetNotes() {
var checker = new Role(3333333333, "checker", "makes sure that everything is done well in the project");
assertEquals("makes sure that everything is done well in the project", checker.getNotes());
}
}
