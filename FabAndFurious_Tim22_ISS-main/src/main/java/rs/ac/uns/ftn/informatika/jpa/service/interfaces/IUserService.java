package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.ac.uns.ftn.informatika.jpa.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {

    List<User> getAll();

    void deleteAccount(Long id);

    Optional<User> getUser(String id);

    Page<User> findAll(Pageable page);

    void add(User user);

    boolean existsById(String id);

    Optional<User> findByEmail(String email);

    void processOAuthPostLogin(String email, String name);

    boolean resetPassword(Long userId, String oldPassword, String newPassword);

}
