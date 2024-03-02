package com.coco.pibackend.controller;

import com.coco.pibackend.Dto.LoginDto;
import com.coco.pibackend.Dto.SignupDto;
import com.coco.pibackend.Entity.ERole;
import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Security.jwt.JwtUtils;
import com.coco.pibackend.Security.services.UserDetailsImpl;
import com.coco.pibackend.repository.RoleRepository;
import com.coco.pibackend.repository.UserRepository;
import com.coco.pibackend.response.JwtResponse;
import com.coco.pibackend.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Operation(description = "signin")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.getFirstname(),
                userDetails.getLastename(),
                userDetails.getCin(),
                userDetails.getPhone()));
    }

    @Operation(description = "signup")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signupDto) {
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }



        // Create new user's account
        User user = new User(signupDto.getUsername(),
                signupDto.getEmail(),
                encoder.encode(signupDto.getPassword()));
        user.setFirstName(signupDto.getFirstname());
        user.setLasteName(signupDto.getLastename());
        user.setCin(signupDto.getCin());
        user.setPhone(signupDto.getPhone());


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_MEMBRE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);



        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
