package org.example.multiple.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminRegistrationController {

    @GetMapping(value = "/registration")
    public ResponseEntity<?> registerAdmin() {

        return ResponseEntity.ok("OK");

    }

}
