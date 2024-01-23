package rs.ac.uns.ftn.informatika.jpa;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.model.enums.*;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.RatingRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JpaExampleApplication {

	@Autowired
	AccommodationRepository accommodationRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ReportRepository reportRepository;
	@Autowired
	RatingRepository ratingRepository;

	public static void main(String[] args) {

		SpringApplication.run(JpaExampleApplication.class, args);

	}

	@Bean
	protected InitializingBean sendDatabase() {
		return this::databaseInitialization;
	}

	private void databaseInitialization() {

		List<String> availability1 = Arrays.asList("01/15/2024", "01/16/2024","01/17/2024", "01/18/2024","01/19/2024", "01/20/2024","01/21/2024", "01/22/2024", "01/23/2024","01/24/2024", "01/25/2024", "01/26/2024");
		List<String> availability2 = Arrays.asList("01/24/2024", "01/25/2024", "01/26/2024","01/27/2024", "01/28/2024", "01/29/2024");
		List<String> availability3 = Arrays.asList("01/25/2024", "01/26/2024","01/27/2024", "01/28/2024","01/29/2024", "01/30/2024");
		List<String> availability4 = Arrays.asList("01/26/2024", "01/27/2024");
		List<String> availability5 = Arrays.asList("01/15/2024", "01/16/2024","01/17/2024", "01/18/2024","01/19/2024", "01/20/2024");
		List<String> availability6 = Arrays.asList("01/25/2024", "01/26/2024","01/27/2024", "01/28/2024","01/29/2024", "01/30/2024");

		Accommodation a1 = new Accommodation("Apartman Slavica 1", "Comfortable apartment located in a quiet part of the city.", "Bulevar Kralja Petra 1, Novi Sad", AccommodationType.ROOM, true, true, false, true, availability1, Payment.PerPerson, 100, BookingMethod.AUTOMATIC, 2, 5, AccommodationRequestStatus.ACCEPTED, Long.parseLong("4"), 10, 5, "image1");
		Accommodation a3 = new Accommodation("Apartman Slavica 3", "Comfortable apartment located in a quiet part of the city.", "Bulevar Kralja Petra 1, Novi Sad", AccommodationType.ROOM, true, true, false, true, availability2, Payment.PerPerson, 100, BookingMethod.NON_AUTOMATIC, 2, 5, AccommodationRequestStatus.ACCEPTED, Long.parseLong("4"), 10, 5, "image3");
		Accommodation a2 = new Accommodation("Apartman Slavica 2", "Comfortable apartment located in a quiet part of the city.", "Bulevar Kralja Petra 1, Beograd", AccommodationType.ROOM, true, true, true, false, availability3, Payment.PerAccommodation, 100, BookingMethod.AUTOMATIC, 4, 8, AccommodationRequestStatus.PENDING, Long.parseLong("4"), 20, 7, "image2");
		Accommodation a4 = new Accommodation("Apartman Slavica 4", "Comfortable apartment located in a quiet part of the city.", "Bulevar Kralja Petra 1, Beograd", AccommodationType.ROOM, true, true, true, false, availability4, Payment.PerAccommodation, 100, BookingMethod.AUTOMATIC, 4, 8, AccommodationRequestStatus.PENDING, Long.parseLong("4"), 50, 7, "image1");
		Accommodation a5 = new Accommodation("Studio 11", "Spacious studio located in city center.", "Jevrejska 14, Novi Sad", AccommodationType.STUDIO, true, false, true, true, availability5, Payment.PerPerson, 100, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.PENDING, Long.parseLong("4"), 0, 2, "image3");
		Accommodation a6 = new Accommodation("Studio 12", "Spacious studio located in city center.", "Jevrejska 15, Novi Sad", AccommodationType.STUDIO, true, false, true, true, availability6, Payment.PerPerson, 100, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.ACCEPTED, Long.parseLong("4"), 5, 2, "image2");

		accommodationRepository.save(a1);
		accommodationRepository.save(a2);
		accommodationRepository.save(a3);
		accommodationRepository.save(a4);
		accommodationRepository.save(a5);
		accommodationRepository.save(a6);

		Rating r1 = new Rating(1, null, RatingStatus.ACCEPTED,RatingType.HOST, 1, 2,"01/12/2024");
		Rating r2 = new Rating(5, "Great apartment", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 1, 2,"01/13/2024");
		Rating r3 = new Rating(4, "Comfortable apartment", RatingStatus.PENDING, RatingType.ACCOMMODATION, 1, 2,"01/14/2024");
		Rating r4 = new Rating(2, null, RatingStatus.PENDING,RatingType.ACCOMMODATION, 1, 2,"01/15/2024");
		Rating r5 = new Rating(5, "Great", RatingStatus.ACCEPTED,RatingType.HOST, 1, 3,"01/15/2024");

		ratingRepository.save(r1);
		ratingRepository.save(r2);
		ratingRepository.save(r3);
		ratingRepository.save(r4);
		ratingRepository.save(r5);

		Report rr1 = new Report(2, null, null);
		Report rr2 = new Report(null, 4, null);
		Report rr3 = new Report(null, null, 2);
//		reportRepository.save(rr1);
//		reportRepository.save(rr2);
//		reportRepository.save(rr3);

		Reservation reservation1 = new Reservation(1L, "01/16/2024","01/17/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 5000);
		Reservation reservation2 = new Reservation(1L, "01/18/2024","01/18/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 15000);
		Reservation reservation3 = new Reservation(1L, "01/20/2024","01/24/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 15000);
		Reservation reservation4 = new Reservation(2L, "01/24/2024","01/26/2024", ReservationRequestStatus.PENDING, Long.valueOf(2), 1000);
		Reservation reservation5 = new Reservation(2L, "01/23/2024","01/24/2024", ReservationRequestStatus.PENDING, Long.valueOf(2), 10000);
		Reservation reservation6 = new Reservation(2L, "01/27/2024","01/29/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 15000);
		Reservation reservation7 = new Reservation(2L, "01/27/2024","01/29/2024", ReservationRequestStatus.REJECTED, Long.valueOf(2), 15000);
		Reservation reservation8 = new Reservation(1L, "01/19/2024","01/19/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 5000);
		Reservation reservation9 = new Reservation(3L, "01/19/2024","01/19/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 6000);
		reservationRepository.save(reservation1);
		reservationRepository.save(reservation2);
		reservationRepository.save(reservation3);
		reservationRepository.save(reservation4);
		reservationRepository.save(reservation5);
		reservationRepository.save(reservation6);
		reservationRepository.save(reservation7);
		reservationRepository.save(reservation8);
		reservationRepository.save(reservation9);
	}
}