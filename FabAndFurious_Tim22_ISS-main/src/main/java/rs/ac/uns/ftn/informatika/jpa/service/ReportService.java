package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReportService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService implements IReportService {
    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> getAll() {
        return this.reportRepository.findAll();
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void deleteReport(Long id) {
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report with ID " + id + " not found"));

        reportRepository.delete(existingReport);
    }

    @Override
    public Optional<Report> getReport(String id) {
        return  this.reportRepository.findById(Long.parseLong(id));
    }

}
