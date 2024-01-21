package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReportService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReservationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report with ID " + id + " not found"));

        reservationRepository.delete(existingReservation);
    }

    @Override
    public Optional<Reservation> getReservation(String id) {
        return  this.reservationRepository.findById(Long.parseLong(id));
    }

}