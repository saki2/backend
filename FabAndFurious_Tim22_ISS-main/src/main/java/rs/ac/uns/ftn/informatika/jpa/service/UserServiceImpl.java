package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Reservation;
import rs.ac.uns.ftn.informatika.jpa.model.User;
import rs.ac.uns.ftn.informatika.jpa.model.enums.ReservationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.model.enums.Role;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder, ReservationRepository reservationRepository, AccommodationRepository accommodationRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public void deleteAccount(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NoSuchElementException("User not found with id " + id);
        }

        User user = userOptional.get();

        if (user.getRole() == Role.GUEST) {
            if (reservationRepository.existsByGuestIdAndStatusAndStartDateAfter(
                    id, ReservationRequestStatus.ACCEPTED, LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))) {
                throw new IllegalStateException("Guest has active future reservations and cannot delete the account.");
            }
        }

        if (user.getRole() == Role.HOST) {
            List<Accommodation> accommodations = accommodationRepository.findAllByHostId(id);
            List<Long> accommodationIds = accommodations.stream()
                    .map(Accommodation::getId)
                    .collect(Collectors.toList());

            if (reservationRepository.existsByAccommodationIdInAndStatusAndStartDateAfter(
                    accommodationIds, ReservationRequestStatus.ACCEPTED, LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))) {
                throw new IllegalStateException("Host has active future reservations for their accommodations and cannot delete the account.");
            }

            accommodations.forEach(accommodation -> accommodation.setDeleted(true));
            accommodationRepository.saveAll(accommodations);
        }

        // Logičko brisanje naloga korisnika
        user.setDeleted(true);
        userRepository.save(user);
    }


    public List<User> getAll() {
        return (List<User>) this.userRepository.findAll();
    }

    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public Optional<User> getUser(String id) {
        return  this.userRepository.findById(Long.parseLong(id));
    }

    public void add(User user) {
        this.userRepository.save(user);
    }

    public boolean existsById(String id) {
        return  this.userRepository.existsById(Long.parseLong(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void processOAuthPostLogin(String email, String name) {
        User existUser = this.userRepository.findByEmail(email).get();

        if (existUser == null) {
            User newUser = new User();
            newUser.setFirstName(name.split(" ")[0]);
            newUser.setLastName(name.split(" ")[1]);
            newUser.setEmail(email);
            newUser.setRole(Role.HOST);

            this.userRepository.save(newUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    @Override
    public boolean resetPassword(Long userId, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
