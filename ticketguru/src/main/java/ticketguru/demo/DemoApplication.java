package ticketguru.demo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Profile;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.domain.Event;
import ticketguru.demo.domain.Role;
import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketSale;
import ticketguru.demo.domain.TicketType;
import ticketguru.demo.repositories.EventRepository;
import ticketguru.demo.repositories.RoleRepository;
import ticketguru.demo.repositories.TicketRepository;
import ticketguru.demo.repositories.TicketSaleRepository;
import ticketguru.demo.repositories.TicketTypeRepository;
import ticketguru.demo.repositories.UserRepository;
@SpringBootApplication
public class DemoApplication {
public static void main(String[] args) {
SpringApplication.run(DemoApplication.class, args);
}
@Bean
@Profile("dev")
public CommandLineRunner demo(EventRepository eventRepository,
RoleRepository roleRepository,
UserRepository userRepository,
TicketTypeRepository ticketTypeRepository,
TicketSaleRepository ticketSaleRepository,
TicketRepository ticketRepository) {
return (args) -> {
// --- Create events ---
Event rockEvent = eventRepository.save(new Event("Rock Night", "Arena", "Helsinki",
        LocalDate.of(2026, 10, 10), "Live rock music", 500));

Event jazzEvent = eventRepository.save(new Event("Jazz Sunday", "Jazz Club", "Tampere",
    LocalDate.of(2026, 11, 2), "Smooth jazz evening", 150));

Event techEvent = eventRepository.save(new Event("Tech Expo", "Messukeskus", "Espoo",
    LocalDate.of(2026, 12,20), "Technology and innovation fair", 1000));

Event rapFestival = eventRepository.save(new Event("Rap Festial", "Myyrmanni", "Vantaa",
    LocalDate.of(2026, 9, 15), "Rapping show in the lounge", 750));

Event popConcert = eventRepository.save(new Event(
    "Pop Explosion", "Hartwall Arena", "Helsinki",
    LocalDate.of(2026, 8, 20), "Top pop artists performing live", 1200));

Event classicalEvening = eventRepository.save(new Event(
    "Classical Evening", "Finnish National Opera", "Helsinki",
    LocalDate.of(2026, 9, 5), "Orchestra and solo performances", 300));

Event edmFestival = eventRepository.save(new Event(
    "EDM Beats", "Ratinan Stadion", "Tampere",
    LocalDate.of(2026, 7, 15), "Electronic music festival with DJs", 2000));

Event theatrePlay = eventRepository.save(new Event(
    "Shakespeare in Action", "Espoo City Theatre", "Espoo",
    LocalDate.of(2026, 11, 18), "Classic Shakespeare play performed live", 250));
Event disneyOnIce = eventRepository.save(new Event(
    "Disney on Ice", "Veikkaus Arena", "Helsinki",
    LocalDate.of(2026, 12, 5), "Magical Disney on Ice show for all ages", 3000));


// --- Create roles ---
Role sellerRole = new Role();
sellerRole.setRoleName("MYYJÄ");
roleRepository.save(sellerRole);
Role adminRole = new Role();
adminRole.setRoleName("ADMIN");
roleRepository.save(adminRole);

PasswordEncoder encoder = new BCryptPasswordEncoder();

// --- Create users ---
AppUser demoUser1 = new AppUser();
demoUser1.setUsername("oskari");
demoUser1.setPasswordHash(encoder.encode("demo1"));
demoUser1.setFirstname("Oskari");
demoUser1.setLastname("Uninen");
demoUser1.setEmail("oskari.uninen@gmail.com");
demoUser1.setPhone("12345678");
demoUser1.setRole(sellerRole);
userRepository.save(demoUser1);
AppUser demoUser2 = new AppUser();
demoUser2.setUsername("jaska");
demoUser2.setPasswordHash(encoder.encode("demo2"));
demoUser2.setFirstname("Jaska");
demoUser2.setLastname("Jokunen");
demoUser2.setEmail("jaska.jokunen@hotmail.com");
demoUser2.setPhone("87654321");
demoUser2.setRole(adminRole);
userRepository.save(demoUser2);

// --- Create ticket types ---
TicketType adult = ticketTypeRepository.save(new TicketType("Adult", 30.0));
TicketType child = ticketTypeRepository.save(new TicketType("Child", 15.0));
TicketType senior = ticketTypeRepository.save(new TicketType("Senior", 20.0));
TicketType student = ticketTypeRepository.save(new TicketType("Student", 25.0));
// --- Create ticket sales ---
// Sale 1 for demoUser1
TicketSale sale1 = new TicketSale();
sale1.setUser(demoUser1);
sale1.setDateTime(LocalDateTime.now());
sale1.setPrice(BigDecimal.valueOf(60.0)); // 2 adult tickets
ticketSaleRepository.save(sale1);
// Tickets for sale1
Ticket ticket1 = new Ticket();
ticket1.setTicketCode(1001L);
ticket1.setTicketTypeId(adult);
ticket1.setEventId(rockEvent);
ticket1.setTicketSale(sale1);
ticket1.setUsed(false);
ticketRepository.save(ticket1);
Ticket ticket2 = new Ticket();
ticket2.setTicketCode(1002L);
ticket2.setTicketTypeId(adult);
ticket2.setEventId(rockEvent);
ticket2.setTicketSale(sale1);
ticket2.setUsed(false);
ticketRepository.save(ticket2);
// Sale 2 for demoUser2
TicketSale sale2 = new TicketSale();
sale2.setUser(demoUser2);
sale2.setDateTime(LocalDateTime.now());
sale2.setPrice(BigDecimal.valueOf(45.0)); // 1 adult + 1 child
ticketSaleRepository.save(sale2);
Ticket ticket3 = new Ticket();
ticket3.setTicketCode(2001L);
ticket3.setTicketTypeId(adult);
ticket3.setEventId(jazzEvent);
ticket3.setTicketSale(sale2);
ticket3.setUsed(false);
ticketRepository.save(ticket3);
Ticket ticket4 = new Ticket();
ticket4.setTicketCode(2002L);
ticket4.setTicketTypeId(child);
ticket4.setEventId(jazzEvent);
ticket4.setTicketSale(sale2);
ticket4.setUsed(false);
ticketRepository.save(ticket4);

// --- Sale 3 for demoUser1 ---
TicketSale sale3 = new TicketSale();
sale3.setUser(demoUser1);
sale3.setDateTime(LocalDateTime.now());
sale3.setPrice(BigDecimal.valueOf(90.0)); // 3 adult tickets
ticketSaleRepository.save(sale3);

Ticket ticket5 = new Ticket();
ticket5.setTicketCode(1003L);
ticket5.setTicketTypeId(adult);
ticket5.setEventId(techEvent);
ticket5.setTicketSale(sale3);
ticket5.setUsed(false);
ticketRepository.save(ticket5);

Ticket ticket6 = new Ticket();
ticket6.setTicketCode(1004L);
ticket6.setTicketTypeId(adult);
ticket6.setEventId(techEvent);
ticket6.setTicketSale(sale3);
ticket6.setUsed(false);
ticketRepository.save(ticket6);

Ticket ticket7 = new Ticket();
ticket7.setTicketCode(1005L);
ticket7.setTicketTypeId(adult);
ticket7.setEventId(techEvent);
ticket7.setTicketSale(sale3);
ticket7.setUsed(false);
ticketRepository.save(ticket7);

// --- Sale 4 for demoUser2 ---
TicketSale sale4 = new TicketSale();
sale4.setUser(demoUser2);
sale4.setDateTime(LocalDateTime.now());
sale4.setPrice(BigDecimal.valueOf(55.0)); // 1 adult + 1 child
ticketSaleRepository.save(sale4);

Ticket ticket8 = new Ticket();
ticket8.setTicketCode(2003L);
ticket8.setTicketTypeId(adult);
ticket8.setEventId(rapFestival);
ticket8.setTicketSale(sale4);
ticket8.setUsed(false);
ticketRepository.save(ticket8);

Ticket ticket9 = new Ticket();
ticket9.setTicketCode(2004L);
ticket9.setTicketTypeId(student);
ticket9.setEventId(rapFestival);
ticket9.setTicketSale(sale4);
ticket9.setUsed(false);
ticketRepository.save(ticket9);


};
}
}
