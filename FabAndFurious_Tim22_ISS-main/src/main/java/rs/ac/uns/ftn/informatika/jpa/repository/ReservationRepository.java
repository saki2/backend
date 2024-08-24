package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);
    List<Reservation> findByGuestId(Long hostId);
    List<Reservation> findByAccommodationId(Long accommodationId);

    boolean existsByGuestIdAndStatus(Long guestId, ReservationRequestStatus status);
    boolean existsByAccommodationIdInAndStatus(List<Long> accommodationIds, ReservationRequestStatus status);

    List<Reservation> findByGuestIdAndStatusAndStartDateAfter(Long guestId, ReservationRequestStatus status, LocalDate date);
    List<Reservation> findByAccommodationIdInAndStatusAndStartDateAfter(List<Long> accommodationIds, ReservationRequestStatus status, LocalDate date);

//    List<Reservation> findByHostId(Long hostId);
//    List<Reservation> findByGuestId(Long guestId);

    boolean existsByGuestIdAndStatusAndStartDateAfter(Long guestId, ReservationRequestStatus status, String startDate);

    boolean existsByAccommodationIdInAndStatusAndStartDateAfter(List<Long> accommodationIds, ReservationRequestStatus status, String startDate);

    int countByGuestIdAndStatus(Long guestId, ReservationRequestStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.accommodationId IN (SELECT a.id FROM Accommodation a WHERE a.hostId = :hostId)")
    List<Reservation> findByHostId(@Param("hostId") Long hostId);
}