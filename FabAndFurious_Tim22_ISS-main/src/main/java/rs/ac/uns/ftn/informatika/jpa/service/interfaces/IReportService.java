package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.Report;

import java.util.List;

public interface IReportService {
    List<Report> getAll();
    Report saveReport(Report report);
    void deleteReport(Long id);
}
