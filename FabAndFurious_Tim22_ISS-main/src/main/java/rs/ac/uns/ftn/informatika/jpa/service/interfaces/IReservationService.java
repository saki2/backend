package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.dto.response.ReservationAndAccommodationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    List<Reservation> getAll();
    Reservation saveReservation(Reservation reservation);
    void deleteReservation(Long id);
    Optional<Reservation> getReservation(String id);
    List<Reservation> findByGuestId(Long guestId);
    List<Reservation> findByHostId(Long hostId);
    List<Reservation> findByAccommodationId(Long accommodationId);
    Reservation acceptReservation(Long id);
    Reservation declineReservation(Long id);
    Reservation cancelReservation(Long id);
    List<ReservationAndAccommodationDTO> getHostReservationAndAccommodationName(Long hostId);

    List<ReservationAndAccommodationDTO> getGuestReservationAndAccommodationName(Long guestId);

    void deleteReservationRequest(Long reservationId);
}
