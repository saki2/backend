package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);
    List<Reservation> findByGuestId(Long hostId);
    List<Reservation> findByAccommodationId(Long accommodationId);

//    List<Reservation> findByHostId(Long hostId);
//    List<Reservation> findByGuestId(Long guestId);
}