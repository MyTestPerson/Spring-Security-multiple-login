package org.example.multiple.controllers;

import org.example.multiple.models.Seller;
import org.example.multiple.models.payload.SellerSignupRequest;
import org.example.multiple.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/seller")
public class SellerRegistrationController {

    @Autowired
    SellerRepository sellerRepository;


    @Autowired
    PasswordEncoder encoder;


    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerSeller(@RequestBody SellerSignupRequest sellerSignupRequest) {


        Seller seller = new Seller();
        seller.setEmail(sellerSignupRequest.email());
        seller.setPassword(sellerSignupRequest.password());
        seller.setPassword(encoder.encode(seller.getPassword()));

        sellerRepository.save(seller);

        return ResponseEntity.ok("OK");

    }


}
