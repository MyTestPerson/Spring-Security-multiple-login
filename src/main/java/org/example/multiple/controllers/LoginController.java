package org.example.multiple.controllers;

import org.example.multiple.models.payload.request.LoginRequest;
import org.example.multiple.models.payload.response.JwtResponse;
import org.example.multiple.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LoginController {

  private final
  AuthenticationManager authenticationManager;

  private final
  JwtUtils jwtUtils;


  public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {


      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtUtils.generateJwtToken(authentication);

      return ResponseEntity.ok(new JwtResponse(jwt));


  }

}
