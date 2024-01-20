package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    List<Accommodation> findByHostId(Long hostId);

}
