package org.example.multiple.controllers;

import org.example.multiple.enam.RoleEnum;
import org.example.multiple.models.RoleUser;
import org.example.multiple.models.User;
import org.example.multiple.models.payload.request.SignupRequest;
import org.example.multiple.repository.RoleRepository;
import org.example.multiple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RegistrationUserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

        User user = new User();
        user.setEmail(signUpRequest.email());
        user.setPassword(signUpRequest.password());
        user.setPassword(encoder.encode(user.getPassword()));

        Set<RoleUser> roleUsers = new LinkedHashSet<>();
        RoleUser userRoleUser = roleRepository.findByRoleEnum(RoleEnum.ROLE_USER);
        roleUsers.add(userRoleUser);
        user.setRoleUsers(roleUsers);
        userRepository.save(user);

        return ResponseEntity.ok("OK");

    }


}
