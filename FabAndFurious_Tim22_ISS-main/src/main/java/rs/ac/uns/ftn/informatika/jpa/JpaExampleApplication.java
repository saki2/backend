package rs.ac.uns.ftn.informatika.jpa;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JpaExampleApplication {

	@Autowired
	AccommodationRepository accommodationRepository;

	public static void main(String[] args) {

		SpringApplication.run(JpaExampleApplication.class, args);

	}

	@Bean
	protected InitializingBean sendDatabase() {
		return this::databaseInitialization;
	}

	private void databaseInitialization() {
		Accommodation accommodation = new Accommodation();
		accommodation.setName("imexx");
		accommodationRepository.save(accommodation);
	}
}