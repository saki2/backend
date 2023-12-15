package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.dto.request.*;
import rs.ac.uns.ftn.informatika.jpa.model.enums.Role;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/host")
public class HostController {

    private final IHostService hostService;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;


    public HostController(IHostService hostService, IUserService userService, PasswordEncoder passwordEncoder) {
        this.hostService = hostService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewHost(@Valid @RequestBody RequestHostDTO requestHostDTO) {

        Host host = requestHostDTO.parseToHost();
        host.setPassword(passwordEncoder.encode(requestHostDTO.getPassword()));
        host.setRole(Role.HOST);

        hostService.add(host);

        return new ResponseEntity<>(host.parseToResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Host> getHost(@PathVariable("id") String id) {

        Host host = this.hostService.getHost(id).get();

        return ResponseEntity.ok(host);
    }
}
