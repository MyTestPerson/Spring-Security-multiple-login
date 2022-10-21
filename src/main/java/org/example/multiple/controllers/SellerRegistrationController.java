package org.example.multiple.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/seller")
public class SellerRegistrationController {

    @GetMapping(value = "/registration")
    public ResponseEntity<?> registerSeller() {

        return ResponseEntity.ok("OK");

    }

}
