package rs.ac.uns.ftn.informatika.jpa.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.dto.request.*;
import rs.ac.uns.ftn.informatika.jpa.dto.response.*;
import rs.ac.uns.ftn.informatika.jpa.model.*;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.*;
import rs.ac.uns.ftn.informatika.jpa.util.TokenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenUtils tokenUtils;

    @Autowired
    public UserController(IUserService userService, TokenUtils tokenUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenUtils = tokenUtils;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(Pageable page) {

        Page<User> users = userService.findAll(page);
        int size = userService.getAll().size();
        List<ResponseUserWithIdDTO> responseUserDTOS = new ArrayList<>();
        for (User u : users) {
            responseUserDTOS.add(u.parseToResponseUserWithId());
        }
        return new ResponseEntity<>(new ResponsePageDTO(size, Arrays.asList(responseUserDTOS.toArray())), HttpStatus.OK);
    }


    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody RequestLoginDTO login) {
        try {
            User user = this.userService.findByEmail(login.getEmail()).get();
            if(!user.isBlocked()){
            ResponseLoginDTO responseLogin = new ResponseLoginDTO();
            responseLogin.setAccessToken(this.tokenUtils.generateToken(user));
            responseLogin.setRefreshToken(this.tokenUtils.generateRefreshToken(user));
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(responseLogin, HttpStatus.OK);}
            return new ResponseEntity<>("User is BLOCKED!", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>("Wrong username or password!", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return null;
    }

    @PutMapping(value = "/{id}/block")
    public ResponseEntity<?> blockUser(@PathVariable("id") String id) {
        User user = userService.getUser(id).get();
        if (user.isBlocked()) {
            return new ResponseEntity<>("User already blocked!", HttpStatus.BAD_REQUEST);
        }
        user.setBlocked(true);
        userService.add(user);
        return new ResponseEntity<>("User is successfully blocked", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/unblock")
    public ResponseEntity<?> ublockUser(@PathVariable("id") String id) {

        if (!StringUtils.isNumeric(id)) {
            return new ResponseEntity<>("Id is not numeric", HttpStatus.NOT_FOUND);
        }
        if (!userService.existsById(id)) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id).get();
        if (!user.isBlocked()) {
            return new ResponseEntity<>("User already unblocked!", HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(false);
        userService.add(user);
        return new ResponseEntity<>("User is successfully ublocked", HttpStatus.NO_CONTENT);
    }


}
