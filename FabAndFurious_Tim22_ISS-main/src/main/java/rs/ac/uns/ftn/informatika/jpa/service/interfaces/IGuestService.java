package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.Guest;

import java.util.List;
import java.util.Optional;

public interface IGuestService {
    List<Guest> getAll();

    Optional<Guest> getGuest(String id);

    void add(Guest guest);

}
