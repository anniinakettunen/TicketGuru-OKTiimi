package ticketguru.demo;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.Role;
public class RoleTest {
public final Role designer = new Role(1, "designer", "makes instructions for actions in the project");
@Test
void getRoleIdIsPresentAndReturnsTheRoleId() throws Exception {
checkMethod(designer, "getRoleId", 1);
}
@Test
void getRoleNameIsPresentAndReturnsTheRoleName() throws Exception {
checkMethod(designer, "getRoleName", "designer");
}
@Test
void getNotesIsPresentAndReturnsTheNotes() throws Exception {
checkMethod(designer, "getNotes", "makes instructions for actions in the project");
}
}
