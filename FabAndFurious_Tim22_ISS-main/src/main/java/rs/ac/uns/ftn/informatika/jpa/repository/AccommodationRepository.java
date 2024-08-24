package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    List<Accommodation> findByHostId(Long hostId);

    List<Long> findIdsByHostId(Long hostId);
    void deleteAllByHostId(Long hostId);

    List<Accommodation> findByDeletedFalse();
    List<Accommodation> findAllByHostId(Long hostId);

    @Query("SELECT a FROM Accommodation a WHERE a.location = :location AND a.minGuest <= :numberOfGuests AND a.maxGuest >= :numberOfGuests")
    List<Accommodation> findByLocationAndGuests(@Param("location") String location, @Param("numberOfGuests") int numberOfGuests);


}
