package org.example.multiple.controllers;

import org.example.multiple.models.Admin;
import org.example.multiple.models.payload.AdminSignupRequest;
import org.example.multiple.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminRegistrationController {


    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminSignupRequest adminSignupRequest) {

        Admin admin = new Admin();
        admin.setEmail(adminSignupRequest.email());
        admin.setPassword(adminSignupRequest.password());
        admin.setPassword(encoder.encode(admin.getPassword()));

        adminRepository.save(admin);

        return ResponseEntity.ok("OK");

    }


}
