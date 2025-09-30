package ticketguru.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ticketguru.demo.domain.Event;
import ticketguru.demo.domain.Role;
import ticketguru.demo.domain.Ticket;
import ticketguru.demo.domain.TicketType;
import ticketguru.demo.domain.AppUser;
import ticketguru.demo.repositories.EventRepository;
import ticketguru.demo.repositories.RoleRepository;
import ticketguru.demo.repositories.TicketTypeRepository;
import ticketguru.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EventRepository eventRepository,
                                  RoleRepository roleRepository,
                                  UserRepository userRepository,
                                  TicketTypeRepository ticketTypeRepository) {
        return (args) -> {
            // Test events
            eventRepository.save(new Event("Rock Night", "Arena", "Helsinki",
                    LocalDate.of(2025, 10, 5), "Live rock music", 500));
            eventRepository.save(new Event("Jazz Sunday", "Jazz Club", "Tampere",
                    LocalDate.of(2025, 11, 2), "Smooth jazz evening", 150));
            eventRepository.save(new Event("Tech Expo", "Messukeskus", "Espoo",
                    LocalDate.of(2025, 9, 28), "Technology and innovation fair", 1000));

            // Demo role
            Role demoRole = new Role();
            demoRole.setRoleName("myyjä");
            roleRepository.save(demoRole);

            // Demo user 1
            AppUser demoUser1 = new AppUser();
            demoUser1.setFirstname("Demo1");
            demoUser1.setLastname("User1");
            demoUser1.setEmail("demo1@example.com");
            demoUser1.setPhone("12345678");
            demoUser1.setRole(demoRole);
            userRepository.save(demoUser1);

            // Demo user 2
            AppUser demoUser2 = new AppUser();
            demoUser2.setFirstname("Demo2");
            demoUser2.setLastname("User2");
            demoUser2.setEmail("demo2@example.com");
            demoUser2.setPhone("87654321");
            demoUser2.setRole(demoRole);
            userRepository.save(demoUser2);

            // Demo ticket types
            TicketType adult = new TicketType("Adult", 30.0);
            TicketType child = new TicketType("Child", 15.0);
            TicketType senior = new TicketType("Senior", 20.0);
            ticketTypeRepository.save(adult);
            ticketTypeRepository.save(child);
            ticketTypeRepository.save(senior);
        };
    }
}
