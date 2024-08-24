package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IGuestService;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService  implements IGuestService {

    private GuestRepository guestRepository;

    private ReservationRepository reservationRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Guest> getAll() {
        return this.guestRepository.findAll();
    }

    @Override
    public Optional<Guest> getGuest(String id) {
        return this.guestRepository.findById(Long.parseLong(id));
    }

    public void add(Guest guest) {
        this.guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(Guest existingGuest, Guest updatedGuest) {
        existingGuest.setFirstName(updatedGuest.getFirstName());
        existingGuest.setLastName(updatedGuest.getLastName());
        existingGuest.setEmail(updatedGuest.getEmail());
        existingGuest.setAddress(updatedGuest.getAddress());
        existingGuest.setPhoneNumber(updatedGuest.getPhoneNumber());

        return this.guestRepository.save(existingGuest);
    }

    public int getCancelledReservationsCount(Long guestId) {
        return reservationRepository.countByGuestIdAndStatus(guestId, ReservationRequestStatus.CANCELED);
    }
}