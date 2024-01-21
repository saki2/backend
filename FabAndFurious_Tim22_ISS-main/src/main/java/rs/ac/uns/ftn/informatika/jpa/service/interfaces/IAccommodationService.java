package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;

import java.util.List;
import java.util.Optional;

public interface IAccommodationService {
    List<Accommodation> getAll();

    Optional<Accommodation> getAccommodation(String id);

    void add(Accommodation accommodation);

    Accommodation saveAccommodation(Accommodation accommodation);

    List<Accommodation> getAllPendingAccommodations();

    Accommodation acceptAccommodationRequest(Long accommodationId);

    Accommodation rejectAccommodationRequest(Long accommodationId);

    List<Accommodation> findByHostId(Long hostId);
    List<Accommodation> getFavorites(Long guestId);

    void deleteAccommodation(Long id);
}
