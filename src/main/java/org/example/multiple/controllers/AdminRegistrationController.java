package org.example.multiple.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminRegistrationController {

    @GetMapping(value = "/registration")
    public ResponseEntity<?> registerAdmin() {

        return ResponseEntity.ok("OK");

    }

}
