package ticketguru.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class AppUserTest {
@Test
void testGetId() {
var donaldtrump = new AppUser(11111111111, "donaldtrump", "Abc12345", "Donald", "Trump", "donald.trump@gmail.com", "+358406285791", 1111111111);
assertEquals(11111111111, donaldtrump.getId());
}
@Test
void testGetUsername() {
var cristianoronaldo = new AppUser(2222222222, "cristianoronaldo", "Def678910", "Cristiano", "Ronaldo", "cristiano.ronaldo@gmail.com", "+358407685783", 2222222222);
assertEquals("cristianoronaldo", cristianoronaldo.getUsername());
}
@Test
void testGetPasswordHash() {
var elonmusk = new AppUser(3333333333, "elonmusk", "Ghi1112131415", "Elon", "Musk", "@elon.musk@gmail.com", "+358405776388", 3333333333);
assertEquals("Ghi1112131415", elonmusk.getPasswordHash());
}
@Test
void testGetFirstname() {
var gretathunberg = new AppUser(4444444444, "gretathunberg", "Jkl1617181920", "Greta", "Thunberg", "greta.thunberg@gmail.com", "+358403227555", 4444444444);
assertEquals("Greta", gretathunberg.getFirstname());
}
@Test
void testGetLastname() {
var lionelmessi = new AppUser(5555555555, "lionelmessi", "Mno2122232425", "Lionel", "Messi", "lionel.messi@gmail.com", "+358407747903", 5555555555);
assertEquals("Messi", lionelmessi.getLastname());
}
@Test
void testGetEmail() {
var usainbolt = new AppUser(6666666666, "usainbolt", "Pqr2627282930", "Usain", "Bolt", "usain.bolt@gmail.com", "+358400453324", 6666666666);
assertEquals("usain.bolt@gmail.com", usainbolt.getEmail());
}
@Test
void testGetPhone() {
var jamieoliver = new AppUser(7777777777, "jamieoliver", "Stu3132333435", "Jamie", "Oliver", "jamie.oliver@gmail.com", "+358408254612", 7777777777);
assertEquals("+358408254612", jamieoliver.getPhone());
}
@Test
void testGetRole() {
var andreapirlo = new AppUser(8888888888, "andreapirlo", "Vwx3637383940", "Andrea", "Pirlo", "andrea.pirlo@gmail.com", "+358404879837", 8888888888);
assertEquals(8888888888, andreapirlo.getRole());
}   
}
