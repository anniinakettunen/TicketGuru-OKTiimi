package ticketguru.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticketguru.demo.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByReportId(Long reportid);

    List<Report> findByEventId(Long eventid);
    
}
