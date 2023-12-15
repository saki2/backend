package rs.ac.uns.ftn.informatika.jpa.model;

import lombok.*;
import rs.ac.uns.ftn.informatika.jpa.dto.response.ResponseHostDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Host extends User {
    @OneToMany
    private List<Accommodation> accommodations;
    @OneToMany
    private List<Rating> ratings;

    public Host(String firstName, String lastName, String picture, String phoneNumber, String email, String address, String password) {
        super(firstName, lastName, picture, phoneNumber, email, address, password);
    }

    public Host(String name, String surname, String profilePicture, String telephoneNumber, String email, String address, String password, boolean b, boolean b1) {
    }
    public ResponseHostDTO parseToResponse(){
        return new ResponseHostDTO(this.getId(), this.getFirstName(), this.getLastName(), this.getPicture(), this.getPhoneNumber(), this.getEmail(), this.getAddress());
    }
}
