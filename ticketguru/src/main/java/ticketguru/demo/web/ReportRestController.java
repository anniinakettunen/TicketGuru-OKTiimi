package ticketguru.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketguru.demo.domain.Report;
import ticketguru.demo.repositories.ReportRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {

    @Autowired
    private ReportRepository reportRepository;

    // GET: Hae kaikki raportit
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // GET: Hae raportti ID:n perusteella
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        return report.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Luo uusi raportti
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportRepository.save(report);
    }

    // PUT: Päivitä raportti ID:n perusteella
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long reportId, @RequestBody Report reportDetails) {
        Optional<Report> optionalReport = reportRepository.findById(reportId);
        if (optionalReport.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Report report = optionalReport.get();
        report.setName(reportDetails.getName());
        report.setDate(reportDetails.getDate());
        report.setDescription(reportDetails.getDescription());
        report.setEventId(reportDetails.getEventId());
        Report updatedReport = reportRepository.save(report);
        return ResponseEntity.ok(updatedReport);
    }

    // DELETE: Poista raportti ID:n perusteella
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long reportId) {
        if (!reportRepository.existsById(reportId)) {
            return ResponseEntity.notFound().build();
        }
        reportRepository.deleteById(reportId);
        return ResponseEntity.noContent().build();
    }
}
