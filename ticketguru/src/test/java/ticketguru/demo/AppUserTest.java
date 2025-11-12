package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class AppUserTest {
@Test
void testGetId() {
var donaldtrump = new AppUser(1, "donaldtrump", "Abc12345", "Donald", "Trump", "donald.trump@gmail.com", "+358406285791", 1);
assertEquals(1, donaldtrump.getId());
}
@Test
void testGetUsername() {
var cristianoronaldo = new AppUser(2, "cristianoronaldo", "Def678910", "Cristiano", "Ronaldo", "cristiano.ronaldo@gmail.com", "+358407685783", 2);
assertEquals("cristianoronaldo", cristianoronaldo.getUsername());
}
@Test
void testGetPasswordHash() {
var elonmusk = new AppUser(3, "elonmusk", "Ghi1112131415", "Elon", "Musk", "@elon.musk@gmail.com", "+358405776388", 3);
assertEquals("Ghi1112131415", elonmusk.getPasswordHash());
}
@Test
void testGetFirstname() {
var gretathunberg = new AppUser(4, "gretathunberg", "Jkl1617181920", "Greta", "Thunberg", "greta.thunberg@gmail.com", "+358403227555", 4);
assertEquals("Greta", gretathunberg.getFirstname());
}
@Test
void testGetLastname() {
var lionelmessi = new AppUser(5, "lionelmessi", "Mno2122232425", "Lionel", "Messi", "lionel.messi@gmail.com", "+358407747903", 5);
assertEquals("Messi", lionelmessi.getLastname());
}
@Test
void testGetEmail() {
var usainbolt = new AppUser(6, "usainbolt", "Pqr2627282930", "Usain", "Bolt", "usain.bolt@gmail.com", "+358400453324", 6);
assertEquals("usain.bolt@gmail.com", usainbolt.getEmail());
}
@Test
void testGetPhone() {
var jamieoliver = new AppUser(7, "jamieoliver", "Stu3132333435", "Jamie", "Oliver", "jamie.oliver@gmail.com", "+358408254612", 7);
assertEquals("+358408254612", jamieoliver.getPhone());
}
@Test
void testGetRole() {
var andreapirlo = new AppUser(8, "andreapirlo", "Vwx3637383940", "Andrea", "Pirlo", "andrea.pirlo@gmail.com", "+358404879837", 8);
assertEquals(8, andreapirlo.getRole());
}   
}

