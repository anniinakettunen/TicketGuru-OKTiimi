package ticketguru.demo;
import org.junit.jupiter.api.Test;
import ticketguru.demo.domain.AppUser;
public class AppUserTest {
private final AppUser donaldtrump = new AppUser(1, "donaldtrump", "Abc12345", "Donald", "Trump", "donald.trump@gmail.com", "+358406285791", 1);
@Test
void getIdIsPresentAndReturnsTheId() throws Exception {
checkMethod(donaldtrump, "getId", 1);
}
@Test
void getUsernameIsPresentAndReturnsTheUsername() throws Exception {
checkMethod(donaldtrump, "getUsername", "donaldtrump");
}
@Test
void getPasswordHashIsPresentAndReturnsThePasswordHash() throws Exception {
checkMethod(donaldtrump, "getPasswordHash", "Abc12345");
}
@Test
void getFirstnameIsPresentAndReturnsTheFirstname() throws Exception {
checkMethod(donaldtrump, "getFirstname", "Donald");
}
@Test
void getLastnameIsPresentAndReturnsTheLastname() throws Exception {
checkMethod(donaldtrump, "getLastname", "Trump");
}
@Test
void getEmailIsPresentAndReturnsTheEmail() throws Exception {
checkMethod(donaldtrump, "getEmail", "donald.trump@gmail.com");
}
@Test
void getPhoneIsPresentAndReturnsThePhone() throws Exception {
checkMethod(donaldtrump, "getPhone", "+358406285791");
}
@Test
void getRoleIsPresentAndReturnsTheRole() throws Exception {
checkMethod(donaldtrump, "getRole", 1);
}
}
