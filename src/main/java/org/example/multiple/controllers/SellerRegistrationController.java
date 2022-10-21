package org.example.multiple.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/seller")
public class SellerRegistrationController {

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registerSeller() {

        return ResponseEntity.ok("OK");

    }

}
