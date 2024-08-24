package rs.ac.uns.ftn.informatika.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.enums.AccommodationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IAccommodationService;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IUserService;

import javax.el.PropertyNotFoundException;
import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService implements IAccommodationService {
    private AccommodationRepository accommodationRepository;
    private GuestRepository guestRepository;
    private IUserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AccommodationService.class);


    public AccommodationService(AccommodationRepository accommodationRepository, GuestRepository guestRepository) {
        this.accommodationRepository = accommodationRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Accommodation> getAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public List<Accommodation> findByHostId(Long hostId) {
        List<Accommodation> hostAccommodations = accommodationRepository.findByHostId(hostId);
        System.out.println(hostAccommodations);
        return hostAccommodations;
    }
    @Override
    public List<Accommodation> getFavorites(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new EntityNotFoundException("Guest with ID " + guestId + " not found"));
        List<Accommodation> favAccommodations = new ArrayList<>();
        for (int favId:guest.getFavoriteAccommodations()){
            favAccommodations.add(accommodationRepository.findById(Long.valueOf(favId)).orElseThrow(() -> new EntityNotFoundException("Guest with ID " + guestId + " not found")));
        }
        return favAccommodations;
    }

    @Override
    public Optional<Accommodation> getAccommodation(String id) {
        return this.accommodationRepository.findById(Long.parseLong(id));
    }

    @Override
    public void add(Accommodation accommodation) {
        this.accommodationRepository.save(accommodation);
    }

//    @Override
//    public void savePropertyImage(MultipartFile file, Long propertyId) {
//        try {
//            logger.info("Creating directory for propertyId: {}", propertyId);
//            createDirectory(propertyId);
//            Path targetPath = Paths.get("../images/" + propertyId, file.getOriginalFilename());
//            logger.info("Copying file to targetPath: {}", targetPath);
//            java.nio.file.Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
//            logger.info("File copied successfully!");
//
//        } catch (IOException e) {
//            logger.error("Error saving property image", e);
//        }
//    }

//    public void createDirectory(Long propertyId) throws IOException {
//        String directoryPath = "../images/" + propertyId;
//        Path directory = Paths.get(directoryPath);
//
//        if (Files.exists(directory) && Files.isDirectory(directory)) {
//            return;
//        } else {
//            Files.createDirectories(directory);
//            System.out.println("Directory created successfully!");
//        }
//    }

    @Override
    public Accommodation saveAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public List<Accommodation> getAllPendingAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        List<Accommodation> pendingAccommodations = new ArrayList<>();
        for (Accommodation a: accommodations) {
            if (a.getStatus() == AccommodationRequestStatus.PENDING){
                pendingAccommodations.add(a);
            }
        }
        return pendingAccommodations;
    }

    @Override
    public Accommodation acceptAccommodationRequest(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(() -> new PropertyNotFoundException("Accommodation with ID " + accommodationId + " not found."));

        accommodation.setStatus(AccommodationRequestStatus.ACCEPTED);

        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation rejectAccommodationRequest(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(() -> new PropertyNotFoundException("Accommodation with ID " + accommodationId + " not found."));

        accommodation.setStatus(AccommodationRequestStatus.REJECTED);

        return accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteAccommodation(Long id) {
        Accommodation existingAccommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Accommodation with ID " + id + " not found"));

        accommodationRepository.delete(existingAccommodation);
    }

    public List<Accommodation> searchAccommodations(String location, int numberOfGuests, String startDate, String endDate) {
        List<Accommodation> accommodations = accommodationRepository.findByLocationAndGuests(location, numberOfGuests);
        List<Accommodation> availableAccommodations = new ArrayList<>();

        for (Accommodation accommodation : accommodations) {
            boolean isAvailable = checkAvailability(accommodation, startDate, endDate);
            if (isAvailable) {
                availableAccommodations.add(accommodation);
            }
        }

        return availableAccommodations;
    }

    private boolean checkAvailability(Accommodation accommodation, String startDate, String endDate) {
        List<String> availabilityDates = fetchAvailabilityDates(accommodation);

        String currentDate = startDate;
        while (!currentDate.equals(endDate)) {
            if (!availabilityDates.contains(currentDate)) {
                return false;
            }
            currentDate = nextDate(currentDate);
        }
        return availabilityDates.contains(endDate);
    }

    private List<String> fetchAvailabilityDates(Accommodation accommodation) {
        return accommodation.getAvailability();
    }

    private String nextDate(String currentDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(currentDate, formatter);
            date = date.plusDays(1);
            return date.format(formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Greška prilikom parsiranja datuma: " + currentDate, e);
        }
    }



}
