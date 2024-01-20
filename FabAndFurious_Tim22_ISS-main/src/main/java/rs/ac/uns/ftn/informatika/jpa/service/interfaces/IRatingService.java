package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;

import java.util.List;
import java.util.Optional;

public interface IRatingService {
    List<Rating> getAll();
    Rating saveRating(Rating rating);

    Rating acceptRatingRequest(Long ratingId);

    Rating rejectRatingRequest(Long ratingId);
    List<Rating> getAllHostRatings(Long hostId);
    List<Rating> getAllAccommodationRatings(Long accommodationId);
    Optional<Rating> getRating(String id);
    void deleteRating(Long id);

}
