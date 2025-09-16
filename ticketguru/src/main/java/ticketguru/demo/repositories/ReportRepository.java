package ticketguru.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import ticketguru.demo.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByReportId(Long reportId);

    List<Report> findByEventId(Event eventId);
    
}
