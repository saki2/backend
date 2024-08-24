package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.dto.response.ReservationAndAccommodationDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IReservationService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {
    private ReservationRepository reservationRepository;
    private AccommodationRepository accommodationRepository;

    public ReservationService(ReservationRepository reservationRepository,AccommodationRepository accommodationRepository) {
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Reservation> getAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report with ID " + id + " not found"));

        reservationRepository.delete(existingReservation);
    }

    @Override
    public Optional<Reservation> getReservation(String id) {
        return  this.reservationRepository.findById(Long.parseLong(id));
    }
    @Override
    public List<Reservation> findByGuestId(Long guestId) {
        List<Reservation> reservations = reservationRepository.findByGuestId(guestId);
        return reservations;
    }

    @Override
    public List<Reservation> findByHostId(Long hostId) {

    List<Accommodation> accommodations = accommodationRepository.findByHostId(hostId);
    List<Reservation> reservations = reservationRepository.findAll();
    List<Reservation> hostReservations = new ArrayList<>();
    for (Reservation r: reservations){
        boolean isAccommodationPresent = accommodations.stream()
                .anyMatch(accommodation -> accommodation.getId().equals((long) r.getAccommodationId()));

        if (isAccommodationPresent) {
            hostReservations.add(r);
        }
    }
    return hostReservations;
    }

    @Override
    public List<Reservation> findByAccommodationId(Long accommodationId) {
        List<Reservation> reservations = reservationRepository.findByAccommodationId(accommodationId);
        return reservations;
    }

    @Override
    public Reservation acceptReservation(Long id) {
        Reservation reservationToAccept = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + id + " not found"));

        reservationToAccept.setStatus(ReservationRequestStatus.ACCEPTED);
        reservationRepository.save(reservationToAccept);

        List<Reservation> overlappingReservations = reservationRepository.findByAccommodationId(reservationToAccept.getAccommodationId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        for (Reservation reservation : overlappingReservations) {
            if (!reservation.getId().equals(id) && reservation.getStatus() == ReservationRequestStatus.PENDING) {
                LocalDate acceptedStartDate = LocalDate.parse(reservationToAccept.getStartDate(), formatter);
                LocalDate acceptedEndDate = LocalDate.parse(reservationToAccept.getEndDate(), formatter);
                LocalDate reservationStartDate = LocalDate.parse(reservation.getStartDate(), formatter);
                LocalDate reservationEndDate = LocalDate.parse(reservation.getEndDate(), formatter);

                if ((reservationStartDate.isBefore(acceptedEndDate) && reservationEndDate.isAfter(acceptedStartDate))
                        || reservationStartDate.equals(acceptedStartDate) || reservationEndDate.equals(acceptedEndDate)) {
                    reservation.setStatus(ReservationRequestStatus.REJECTED);
                    reservationRepository.save(reservation);
                }
            }
        }

        return reservationToAccept;
    }

    @Override
    public Reservation declineReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + id + " not found"));

        reservation.setStatus(ReservationRequestStatus.REJECTED);
        reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public Reservation cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + id + " not found"));

        reservation.setStatus(ReservationRequestStatus.CANCELED);
        reservationRepository.save(reservation);

        return reservation;
    }
    @Override
    public List<ReservationAndAccommodationDTO> getHostReservationAndAccommodationName(Long hostId) {
        List<Reservation> reservations = reservationRepository.findByHostId(hostId);

        return reservations.stream().map(reservation -> {
            Accommodation accommodation = accommodationRepository.findById(reservation.getAccommodationId()).orElse(null);
            String accommodationName = accommodation != null ? accommodation.getName() : "Unknown";
            return new ReservationAndAccommodationDTO(
                    reservation.getId(),
                    reservation.getAccommodationId(),
                    accommodationName,
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getStatus().name(),
                    reservation.getPrice(),
                    reservation.getNumberOfPeople(),
                    reservation.getGuestId()
            );
        }).collect(Collectors.toList());
    }
    @Override
    public List<ReservationAndAccommodationDTO> getGuestReservationAndAccommodationName(Long guestId) {
        List<Reservation> reservations = reservationRepository.findByGuestId(guestId);

        return reservations.stream().map(reservation -> {
            Accommodation accommodation = accommodationRepository.findById(reservation.getAccommodationId()).orElse(null);
            String accommodationName = accommodation != null ? accommodation.getName() : "Unknown";
            return new ReservationAndAccommodationDTO(
                    reservation.getId(),
                    reservation.getAccommodationId(),
                    accommodationName,
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getStatus().name(),
                    reservation.getPrice(),
                    reservation.getNumberOfPeople(),
                    reservation.getGuestId()
            );
        }).collect(Collectors.toList());
    }


    @Override
    public void deleteReservationRequest(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationId + " not found"));

        reservationRepository.delete(reservation);
    }
}