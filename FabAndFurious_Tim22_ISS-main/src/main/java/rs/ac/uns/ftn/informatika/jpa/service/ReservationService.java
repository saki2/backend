package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReportService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReservationService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    private ReservationRepository reservationRepository;
    private AccommodationRepository accommodationRepository;

    public ReservationService(ReservationRepository reservationRepository,AccommodationRepository accommodationRepository) {
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
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
    @Override
    public List<Reservation> findByGuestId(Long guestId) {
        List<Reservation> reservations = reservationRepository.findByGuestId(guestId);
        return reservations;
    }

    @Override
    public List<Reservation> findByHostId(Long hostId) {

    List<Accommodation> accommodations = accommodationRepository.findByHostId(hostId);
    List<Reservation> reservations = reservationRepository.findAll();
    List<Reservation> hostReservations = new ArrayList<>();
    for (Reservation r: reservations){
        boolean isAccommodationPresent = accommodations.stream()
                .anyMatch(accommodation -> accommodation.getId().equals((long) r.getAccommodationId()));

        if (isAccommodationPresent) {
            hostReservations.add(r);
        }
    }
    return hostReservations;
    }

    @Override
    public List<Reservation> findByAccommodationId(Long accommodationId) {
        List<Reservation> reservations = reservationRepository.findByAccommodationId(accommodationId);
        return reservations;
    }

}