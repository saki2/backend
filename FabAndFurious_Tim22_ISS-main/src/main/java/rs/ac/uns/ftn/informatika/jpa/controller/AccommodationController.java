package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.enums.AccommodationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IAccommodationService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IUserService;


import javax.el.PropertyNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/accommodation")
public class AccommodationController {

    @Autowired
    private final IAccommodationService accommodationService;
    private final IUserService userService;
    private final GuestRepository guestRepository;

    public AccommodationController(IAccommodationService accommodationService, IUserService userService,GuestRepository guestRepository) {
        this.accommodationService = accommodationService;
        this.userService = userService;
        this.guestRepository = guestRepository;
    }


    @PostMapping("/add-accommodation")
    public ResponseEntity<Accommodation> addAccommodation(@RequestBody Accommodation accommodation) {
        Accommodation savedAccommodation = accommodationService.saveAccommodation(accommodation);
        return ResponseEntity.ok(savedAccommodation);
    }

    @DeleteMapping("/delete-accommodation/{id}")
    public ResponseEntity<String> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.ok("Accommodation with ID " + id + " deleted successfully");
    }

    @GetMapping("/get-pending-accommodations")
    public ResponseEntity<List<Accommodation>> getAllPendingAccommodations() {
        List<Accommodation> accommodations = accommodationService.getAllPendingAccommodations();
        return ResponseEntity.ok(accommodations);
    }


    @PutMapping("/accept/{accommodation-id}")
    public ResponseEntity<Accommodation> acceptAccommodationRequest(
            @PathVariable("accommodation-id") Long accommodationId) {

        Accommodation updatedAccommodation = accommodationService.acceptAccommodationRequest(accommodationId);

        return ResponseEntity.ok(updatedAccommodation);
    }

    @PutMapping(value="/reject/{accommodation-id}")
    public ResponseEntity<Accommodation> rejectAccommodationRequest(
            @PathVariable("accommodation-id") Long accommodationId) {

        Accommodation updatedAccommodation = accommodationService.rejectAccommodationRequest(accommodationId);

        return ResponseEntity.ok(updatedAccommodation);
    }


    @GetMapping
    public ResponseEntity<List<Accommodation>> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationService.getAll();
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity<List<Accommodation>> getHostAccommodations(@PathVariable("hostId") Long hostId) {

        List<Accommodation> hostAccommodations = accommodationService.findByHostId(hostId);
        return ResponseEntity.ok(hostAccommodations);
    }
    @GetMapping("/favorites/{guestId}")
    public ResponseEntity<List<Accommodation>> getFavoriteAccommodations(@PathVariable("guestId") Long guestId) {

        List<Accommodation> favorites = accommodationService.getFavorites(guestId);
        return ResponseEntity.ok(favorites);
    }
    @DeleteMapping("/removeFavorite/{guestId}/{accommodationId}")
    public ResponseEntity<String> removeFavorite(@PathVariable("guestId") Long guestId,@PathVariable("accommodationId") Long accommodationId) {

        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new PropertyNotFoundException("Guest with ID " + guestId + " not found."));

        //guest.getFavoriteAccommodations().remove(accommodationId.intValue());
        guest.getFavoriteAccommodations().removeIf(id -> id == accommodationId.intValue());

        guestRepository.save(guest);
        return ResponseEntity.ok(guest.getFavoriteAccommodations().toString());
    }
    @PutMapping("/addFavorite/{guestId}/{accommodationId}")
    public ResponseEntity<String> addFavorite(@PathVariable("guestId") Long guestId,@PathVariable("accommodationId") Long accommodationId) {

        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new PropertyNotFoundException("Guest with ID " + guestId + " not found."));

        if (!guest.getFavoriteAccommodations().contains(accommodationId.intValue())) {
            guest.getFavoriteAccommodations().add(accommodationId.intValue());
        }
        guestRepository.save(guest);
        return ResponseEntity.ok(guest.getFavoriteAccommodations().toString());
    }


//    @PutMapping (value = "/{accommodationId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateAccommodation(@PathVariable("accommodationId") String accommodationId, @Valid @RequestBody RequestAccommodationUpdateDTO requestAccommodationUpdateDTO)
//    {
//        Accommodation accommodationForUpdate = accommodationService.getAccommodation(accommodationId).get();
//        accommodationForUpdate.update(requestAccommodationUpdateDTO);
//        accommodationService.add(accommodationForUpdate);
//        return new ResponseEntity<>(accommodationForUpdate.parseToResponse(), HttpStatus.OK);
//    }

//    @PostMapping("/{id}/saveImage")
//    public ResponseEntity<ResponseDto> savePropertyImage(@PathVariable("id") Long propertyId, @RequestParam("image") MultipartFile file) throws IOException, UserNotFoundException {
//        propertyService.savePropertyImage(file, propertyId);
//        return new ResponseEntity<>(new ResponseDto("Property image saved successfully"), HttpStatus.OK);
//    }

    @GetMapping(value = "accommodation/{id}")
    public ResponseEntity<Accommodation> getAccommodation(@PathVariable("id") String id) {

        Accommodation accommodation = this.accommodationService.getAccommodation(id).get();

        return ResponseEntity.ok(accommodation);
    }
}

