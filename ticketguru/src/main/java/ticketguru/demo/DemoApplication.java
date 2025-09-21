package ticketguru.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ticketguru.demo.domain.Event;
import ticketguru.demo.repositories.EventRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EventRepository eventRepository) {
        return (args) -> {
            // Testitapahtumat
            eventRepository.save(new Event("Rock Night", "Arena", "Helsinki", LocalDate.of(2025, 10, 5), "Live rock music", 500));
            eventRepository.save(new Event("Jazz Sunday", "Jazz Club", "Tampere", LocalDate.of(2025, 11, 2), "Smooth jazz evening", 150));
            eventRepository.save(new Event("Tech Expo", "Messukeskus", "Espoo", LocalDate.of(2025, 9, 28), "Technology and innovation fair", 1000));
        };
    }
}

