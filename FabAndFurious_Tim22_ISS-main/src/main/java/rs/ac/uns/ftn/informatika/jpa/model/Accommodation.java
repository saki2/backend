package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;
import rs.ac.uns.ftn.informatika.jpa.model.enums.AccommodationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.model.enums.AccommodationType;
import rs.ac.uns.ftn.informatika.jpa.model.enums.BookingMethod;
import rs.ac.uns.ftn.informatika.jpa.model.enums.Payment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private AccommodationType type;
    private boolean wifi;
    private boolean kitchen;
    private boolean airConditioner;
    private boolean parking;
    @ElementCollection
    private List<Date> availability;
    private Payment payment;
    private int price;
    private BookingMethod bookingMethod;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Rating> ratings;
    @ElementCollection
    private List<String> photos;
    private int minGuest;
    private int maxGuest;
    private AccommodationRequestStatus status;
    private Long hostId;


    public Accommodation(Long id, String name, String description, String location, AccommodationType type, boolean wifi, boolean kitchen, boolean airConditioner, boolean parking, List<Date> availability, Payment payment, int price, BookingMethod bookingMethod, List<Rating> ratings, List<String> photos, int minGuest, int maxGuest, AccommodationRequestStatus status, Long hostId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.airConditioner = airConditioner;
        this.parking = parking;
        this.availability = availability;
        this.payment = payment;
        this.price = price;
        this.bookingMethod = bookingMethod;
        this.ratings = ratings;
        this.photos = photos;
        this.minGuest = minGuest;
        this.maxGuest = maxGuest;
        this.status = status;
        this.hostId = hostId;
    }

    public Accommodation(Long id, String name, String description, String location, AccommodationType type, boolean wifi, boolean kitchen, boolean airConditioner, boolean parking, List<Date> availability, Payment payment, int price, BookingMethod bookingMethod, int minGuest, int maxGuest, AccommodationRequestStatus status, Long hostId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.airConditioner = airConditioner;
        this.parking = parking;
        this.availability = availability;
        this.payment = payment;
        this.price = price;
        this.bookingMethod = bookingMethod;
        this.minGuest = minGuest;
        this.maxGuest = maxGuest;
        this.status = status;
        this.hostId = hostId;
    }

    public Accommodation(String name, String description, String location, AccommodationType type, boolean wifi, boolean kitchen, boolean airConditioner, boolean parking, List<Date> availability, Payment payment, int price, BookingMethod bookingMethod, int minGuest, int maxGuest, AccommodationRequestStatus status, Long hostId) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.airConditioner = airConditioner;
        this.parking = parking;
        this.availability = availability;
        this.payment = payment;
        this.price = price;
        this.bookingMethod = bookingMethod;
        this.minGuest = minGuest;
        this.maxGuest = maxGuest;
        this.status = status;
        this.hostId = hostId;
    }

    public Accommodation(String name, String description, String location, AccommodationType type, boolean wifi, boolean kitchen, boolean airConditioner, boolean parking, List<Date> availability, Payment payment, int price, BookingMethod bookingMethod, List<Rating> ratings, List<String> photos, int minGuest, int maxGuest, AccommodationRequestStatus status, Long hostId) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.airConditioner = airConditioner;
        this.parking = parking;
        this.availability = availability;
        this.payment = payment;
        this.price = price;
        this.bookingMethod = bookingMethod;
        this.ratings = ratings;
        this.photos = photos;
        this.minGuest = minGuest;
        this.maxGuest = maxGuest;
        this.status = status;
        this.hostId = hostId;
    }
}
