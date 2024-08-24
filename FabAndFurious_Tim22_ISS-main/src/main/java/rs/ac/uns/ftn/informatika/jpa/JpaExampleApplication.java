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
	RatingRepository ratingRepository;

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ReportRepository reportRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}

	@Bean
	protected InitializingBean sendDatabase() {
		return this::databaseInitialization;
	}

	private void databaseInitialization() {
		List<String> availability1 = Arrays.asList("08/21/2024", "08/22/2024", "08/23/2024", "08/28/2024", "08/29/2024");
		List<String> availability2 = Arrays.asList("08/24/2024", "08/25/2024");
		List<String> availability3 = Arrays.asList("08/26/2024", "08/27/2024", "08/28/2024");
		List<String> availability4 = Arrays.asList("08/29/2024", "08/30/2024");
		List<String> availability5 = Arrays.asList("08/31/2024", "09/01/2024");
		List<String> availability6 = Arrays.asList("09/02/2024", "09/03/2024");

		Accommodation a1 = new Accommodation("Apartman Slavica 1", "Comfortable apartment in Novi Sad.", "Novi Sad", AccommodationType.ROOM, true, true, false, true, availability1, Payment.PerPerson, 80, BookingMethod.AUTOMATIC, 2, 5, AccommodationRequestStatus.ACCEPTED, 4L, 10, 5, "image1");
		Accommodation a2 = new Accommodation("Apartman Slavica 2", "Comfortable apartment in Beograd.", "Beograd", AccommodationType.ROOM, true, true, true, false, availability2, Payment.PerAccommodation, 120, BookingMethod.AUTOMATIC, 4, 8, AccommodationRequestStatus.ACCEPTED, 4L, 15, 7, "image2");
		Accommodation a3 = new Accommodation("Apartman Slavica 3", "Quiet apartment in Novi Sad.", "Novi Sad", AccommodationType.ROOM, true, true, false, true, availability3, Payment.PerPerson, 75, BookingMethod.NON_AUTOMATIC, 2, 5, AccommodationRequestStatus.ACCEPTED, 4L, 8, 5, "image3");
		Accommodation a4 = new Accommodation("Apartman Slavica 4", "Modern apartment in Beograd.", "Beograd", AccommodationType.ROOM, true, true, true, false, availability4, Payment.PerAccommodation, 110, BookingMethod.AUTOMATIC, 4, 8, AccommodationRequestStatus.PENDING, 4L, 20, 7, "image1");
		Accommodation a5 = new Accommodation("Studio 11", "Studio in Novi Sad.", "Novi Sad", AccommodationType.STUDIO, true, false, true, true, availability5, Payment.PerPerson, 60, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.ACCEPTED, 4L, 5, 2, "image3");
		Accommodation a6 = new Accommodation("Studio 12", "Comfortable studio in Novi Sad.", "Novi Sad", AccommodationType.STUDIO, true, false, true, true, availability6, Payment.PerPerson, 70, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.ACCEPTED, 4L, 5, 2, "image2");

		List<String> availability7 = Arrays.asList("09/04/2024", "09/05/2024");
		List<String> availability8 = Arrays.asList("09/06/2024", "09/07/2024", "09/08/2024");

		Accommodation a7 = new Accommodation("Luxury Apartment", "Luxury apartment in Beograd.", "Beograd", AccommodationType.STUDIO, true, true, true, true, availability7, Payment.PerAccommodation, 200, BookingMethod.AUTOMATIC, 4, 10, AccommodationRequestStatus.ACCEPTED, 5L, 25, 10, "image4");
		Accommodation a8 = new Accommodation("Budget Room", "Budget room in Nis.", "Nis", AccommodationType.ROOM, true, false, false, false, availability8, Payment.PerPerson, 40, BookingMethod.NON_AUTOMATIC, 1, 2, AccommodationRequestStatus.ACCEPTED, 5L, 5, 3, "image5");

		List<String> availability9 = Arrays.asList("09/09/2024", "09/10/2024", "09/11/2024");
		List<String> availability10 = Arrays.asList("09/10/2024", "09/11/2024");
		List<String> availability11 = Arrays.asList("09/11/2024", "09/12/2024", "09/13/2024");

		Accommodation a9 = new Accommodation("Cozy Cottage", "Cozy cottage in Novi Sad.", "Novi Sad", AccommodationType.STUDIO, false, false, true, true, availability9, Payment.PerAccommodation, 150, BookingMethod.AUTOMATIC, 3, 6, AccommodationRequestStatus.ACCEPTED, 4L, 12, 6, "image6");
		Accommodation a10 = new Accommodation("Modern Flat", "Modern flat in Novi Sad.", "Novi Sad", AccommodationType.ROOM, true, true, true, true, availability10, Payment.PerPerson, 85, BookingMethod.NON_AUTOMATIC, 2, 4, AccommodationRequestStatus.PENDING, 5L, 20, 8, "image7");
		Accommodation a11 = new Accommodation("Rustic Cabin", "Rustic cabin in Novi Sad.", "Novi Sad", AccommodationType.STUDIO, true, false, false, true, availability11, Payment.PerAccommodation, 90, BookingMethod.AUTOMATIC, 2, 5, AccommodationRequestStatus.ACCEPTED, 5L, 15, 5, "image8");

		accommodationRepository.save(a1);
		accommodationRepository.save(a2);
		accommodationRepository.save(a3);
		accommodationRepository.save(a4);
		accommodationRepository.save(a5);
		accommodationRepository.save(a6);
		accommodationRepository.save(a7);
		accommodationRepository.save(a8);
		accommodationRepository.save(a9);
		accommodationRepository.save(a10);
		accommodationRepository.save(a11);

		Rating r1 = new Rating(5, "Excellent stay!", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 1, 2, "08/20/2024");
		Rating r2 = new Rating(4, "Good value for money.", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 1, 3, "08/21/2024");
		ratingRepository.save(r1);
		ratingRepository.save(r2);
		Reservation res1 = new Reservation(1L, "08/21/2024", "08/23/2024", ReservationRequestStatus.PENDING, 2L, 150, 2);
		Reservation res2 = new Reservation(1L, "08/24/2024", "08/27/2024", ReservationRequestStatus.ACCEPTED, 2L, 120, 3);
		Reservation res7 = new Reservation(1L, "08/28/2024", "08/29/2024", ReservationRequestStatus.PENDING, 2L, 120, 3);
		Reservation res8 = new Reservation(1L, "08/28/2024", "08/29/2024", ReservationRequestStatus.PENDING, 3L, 120, 3);
		Reservation res3 = new Reservation(2L, "08/24/2024", "08/25/2024", ReservationRequestStatus.PENDING, 2L, 200, 4);
		Reservation res4 = new Reservation(3L, "08/26/2024", "08/28/2024", ReservationRequestStatus.REJECTED, 2L, 85, 2);
		Reservation res5 = new Reservation(1L, "08/31/2024", "09/01/2024", ReservationRequestStatus.ACCEPTED, 2L, 70, 2);
		Reservation res6 = new Reservation(2L, "08/26/2024", "08/29/2024", ReservationRequestStatus.ACCEPTED, 2L, 110, 3);
		//Reservation res7 = new Reservation(7L, "09/04/2024", "09/05/2024", ReservationRequestStatus.ACCEPTED, 1L, 140, 4);
		//Reservation res8 = new Reservation(8L, "09/06/2024", "09/07/2024", ReservationRequestStatus.PENDING, 1L, 90, 2);
		//Reservation res9 = new Reservation(9L, "09/09/2024", "09/11/2024", ReservationRequestStatus.ACCEPTED, 1L, 160, 3);
		//Reservation res10 = new Reservation(10L, "09/12/2024", "09/13/2024", ReservationRequestStatus.PENDING, 1L, 100, 2);

		reservationRepository.saveAll(Arrays.asList(res1, res2, res3, res4, res5, res6, res7, res8));

		List<Rating> ratings = Arrays.asList(
				new Rating(5, "Excellent stay!", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 1, 2, "08/20/2024"),
				new Rating(4, "Good value for money.", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 1, 3, "08/21/2024"),
				new Rating(3, "Average experience.", RatingStatus.PENDING, RatingType.ACCOMMODATION, 2, 2, "08/22/2024"),
				new Rating(2, "Not as expected.", RatingStatus.PENDING, RatingType.ACCOMMODATION, 3, 3, "08/23/2024"),
				new Rating(1, "Terrible experience!", RatingStatus.REJECTED, RatingType.ACCOMMODATION, 3, 3, "08/24/2024"),
				new Rating(4, "Nice and cozy place.", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 4, 2, "08/25/2024"),
				new Rating(5, "Highly recommended!", RatingStatus.ACCEPTED, RatingType.ACCOMMODATION, 4, 2, "08/26/2024"),
				new Rating(3, "Could be better.", RatingStatus.PENDING, RatingType.ACCOMMODATION, 5, 3, "08/27/2024"),
				new Rating(2, "Wouldn't stay again.", RatingStatus.REJECTED, RatingType.ACCOMMODATION, 6, 2, "08/28/2024"),
				new Rating(4, "Good, but pricey.", RatingStatus.PENDING, RatingType.ACCOMMODATION, 7, 3, "08/29/2024")
		);

		ratingRepository.saveAll(ratings);

		List<Report> reports = Arrays.asList(
				new Report(r1.getId().intValue(), null, null), // Prijava za ocenu
				new Report(r2.getId().intValue(), null, null), // Prijava za ocenu
				new Report(null, 4, null), // Prijava za vlasnika
				new Report(null, null, 2), // Prijava za gosta
				new Report(null, 5, null), // Prijava za vlasnika
				new Report(null, null, 3) // Prijava za gosta
		);

		reportRepository.saveAll(reports);
	}

}
