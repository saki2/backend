package rs.ac.uns.ftn.informatika.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.dto.request.RequestGuestDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.model.enums.Role;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

    private final IUserService userService;
    private final IGuestService guestService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GuestController(IGuestService guestService, IUserService userService, PasswordEncoder passwordEncoder) {
        this.guestService = guestService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGuest(@Valid @RequestBody RequestGuestDTO requestGuestDTO) throws Exception {

        Guest guest = requestGuestDTO.parseToGuest();
        guest.setPassword(passwordEncoder.encode(requestGuestDTO.getPassword()));
        guest.setRole(Role.GUEST);

        guestService.add(guest);
        return new ResponseEntity<>(guest.parseToResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Guest> getGuest(@PathVariable("id") String id) {

        Guest guest = this.guestService.getGuest(id).get();

        return ResponseEntity.ok(guest);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> updateGuest(@PathVariable("id") String id, @Valid @RequestBody Guest updatedGuest) {
        Optional<Guest> existingGuest = guestService.getGuest(id);

        if (existingGuest.isPresent()) {
            Guest guest = guestService.updateGuest(existingGuest.get(), updatedGuest);
            return ResponseEntity.ok(guest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/cancelledReservations")
    public ResponseEntity<Integer> getCancelledReservationsCount(@PathVariable Long id) {
        int cancelledCount = guestService.getCancelledReservationsCount(id);
        return new ResponseEntity<>(cancelledCount, HttpStatus.OK);
    }


}