package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.dto.request.*;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.enums.Role;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {

    private final IHostService hostService;
    private final IRatingService ratingService;
    private final PasswordEncoder passwordEncoder;


    public HostController(IHostService hostService, IRatingService ratingService, PasswordEncoder passwordEncoder) {
        this.hostService = hostService;
        this.ratingService = ratingService;
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
    @GetMapping(value = "averageRate/{hostId}")
    public ResponseEntity<Double> getHostAverageRate(@PathVariable("hostId") String id) {

        double average;
        int rateNum = 0;
        int rates = 0;
        List<Rating> ratingList = this.ratingService.getAllHostRatings(Long.parseLong(id));
        for (Rating r:ratingList){
            rateNum++;
            rates = rates + r.getRating();
        }
        average = rates/rateNum;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedAverage = decimalFormat.format(average);
        double formattedDouble = Double.parseDouble(formattedAverage);

        return ResponseEntity.ok(formattedDouble);
    }
}
