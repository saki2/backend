package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.Host;

import java.util.List;
import java.util.Optional;

public interface IHostService {
    List<Host> getAll();

    Optional<Host> getHost(String id);

    void add(Host host);

}
