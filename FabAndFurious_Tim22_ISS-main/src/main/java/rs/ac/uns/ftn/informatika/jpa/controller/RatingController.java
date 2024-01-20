package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IAccommodationService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IRatingService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IUserService;

import java.util.List;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private final IRatingService ratingService;

    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/add-rating")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return ResponseEntity.ok(savedRating);
    }

    @PutMapping("/accept/{rating-id}")
    public ResponseEntity<Rating> acceptRatingRequest(
            @PathVariable("rating-id") Long ratingId) {

        Rating updatedRating = ratingService.acceptRatingRequest(ratingId);

        return ResponseEntity.ok(updatedRating);
    }

    @PutMapping("/reject/{rating-id}")
    public ResponseEntity<Rating> rejectRatingRequest(
            @PathVariable("rating-id") Long ratingId) {

        Rating updatedRating = ratingService.rejectRatingRequest(ratingId);

        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/delete-rating/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok("Rating with ID " + id + " deleted successfully");
    }

    @GetMapping("/get-host-ratings/{host-id}")
    public ResponseEntity<List<Rating>> getAllHostRatings(@PathVariable("host-id") Long hostId) {
        List<Rating> ratings = ratingService.getAllHostRatings(hostId);
        return ResponseEntity.ok(ratings);
    }
    @GetMapping("/get-accommodation-ratings/{accommodation-id}")
    public ResponseEntity<List<Rating>> getAllAccommodationRatings(@PathVariable("accommodation-id") Long accommodationId) {
        List<Rating> ratings = ratingService.getAllAccommodationRatings(accommodationId);
        return ResponseEntity.ok(ratings);
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAll();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable("id") String id) {

        Rating rating = this.ratingService.getRating(id).get();

        return ResponseEntity.ok(rating);
    }

}
