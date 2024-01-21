package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    List<Reservation> getAll();
    Reservation saveReservation(Reservation reservation);
    void deleteReservation(Long id);
    Optional<Reservation> getReservation(String id);
}
