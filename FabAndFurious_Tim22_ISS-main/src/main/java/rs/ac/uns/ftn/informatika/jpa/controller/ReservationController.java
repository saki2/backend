package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReportService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReservationService;

import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    @Autowired
    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add-reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation savedRes = reservationService.saveReservation(reservation);
        return ResponseEntity.ok(savedRes);
    }

    @DeleteMapping("/delete-reservation/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation with ID " + id + " deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") String id) {

        Reservation reservation = this.reservationService.getReservation(id).get();

        return ResponseEntity.ok(reservation);
    }
}
