package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IRatingService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReportService;

import java.util.List;

@RestController
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/add-report")
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        Report savedReport = reportService.saveReport(report);
        return ResponseEntity.ok(savedReport);
    }

    @DeleteMapping("/delete-report/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok("Report with ID " + id + " deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAll();
        return ResponseEntity.ok(reports);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Report> getReport(@PathVariable("id") String id) {

        Report report = this.reportService.getReport(id).get();

        return ResponseEntity.ok(report);
    }
}
