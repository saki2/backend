package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.ReportItem;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IAccommodationService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReportService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReservationService;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    @Autowired
    private final IReservationService reservationService;
    private final IAccommodationService accommodationService;

    public ReservationController(IReservationService reservationService, IAccommodationService accommodationService) {
        this.reservationService = reservationService;
        this.accommodationService = accommodationService;
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

    @GetMapping("/guestReservations/{guestId}")
    public ResponseEntity<List<Reservation>> getGuestReservations(@PathVariable("guestId") String guestId) {

        List<Reservation> reservations = reservationService.findByGuestId(Long.valueOf(guestId));
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/hostReservations/{hostId}")
    public ResponseEntity<List<Reservation>> getHostReservations(@PathVariable("hostId") String hostId) {

        List<Reservation> reservations = reservationService.findByHostId(Long.valueOf(hostId));
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/accommodationReservations/{accommodationId}")
    public ResponseEntity<List<Reservation>> getAccommodationReservations(@PathVariable("accommodationId") String accommodationId) {

        List<Reservation> reservations = reservationService.findByAccommodationId(Long.valueOf(accommodationId));
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/hostReport/{hostId}/{start}/{end}")
    public ResponseEntity<List<ReportItem>> getReport(
            @PathVariable("hostId") String hostId,
            @PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        List<ReportItem> reservationList = new ArrayList<>();
        List<Reservation> reservations = reservationService.findByHostId(Long.valueOf(hostId));

        for (Reservation r : reservations) {
            try {
                LocalDate from = LocalDate.parse(r.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                LocalDate to = LocalDate.parse(r.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                if (startDate.isBefore(from) && endDate.isAfter(to) && to.isBefore(LocalDate.now())) {
                    if (r.getStatus() == ReservationRequestStatus.ACCEPTED) {
                        int price = r.getPrice();
                        Accommodation accommodation = accommodationService.getAccommodation(String.valueOf(r.getAccommodationId()))
                                .orElseThrow(() -> new EntityNotFoundException("Accommodation with ID " + r.getAccommodationId() + " not found"));

                        if (accommodation != null) {
                            boolean containsName = false;

                            for (ReportItem item : reservationList) {
                                if (item.getName().equals(accommodation.getName())) {
                                    containsName = true;
                                    item.setProfit(item.getProfit() + accommodation.getPrice());
                                    item.setResNum(item.getResNum() + 1);
                                    break;
                                }
                            }

                            if (!containsName) {
                                ReportItem itemNew = new ReportItem(accommodation.getName(), 1, price);
                                reservationList.add(itemNew);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Došlo je do greške: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(reservationList);
    }

}