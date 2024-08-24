package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.model.Admin;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IAdminService;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService){
        this.adminService = adminService;
    }

//    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<?> getAdmin() {
//        Admin admin = adminService.getAll().get(0);
//        return new ResponseEntity<>(admin.parseToResponse(), HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Admin> getGuest(@PathVariable("id") String id) {

        Admin admin = this.adminService.getAdmin(id).get();

        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        Admin admin = adminService.updateAdmin(id, updatedAdmin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

}
