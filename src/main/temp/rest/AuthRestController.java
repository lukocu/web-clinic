package pl.clinic.api.controller.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pl.clinic.api.controller.rest.payload.request.LoginRequest;
import pl.clinic.api.controller.rest.payload.request.SignupRequest;
import pl.clinic.api.controller.rest.payload.response.JwtResponse;
import pl.clinic.api.controller.rest.payload.response.MessageResponse;
import pl.clinic.business.UserService;
import pl.clinic.business.RoleService;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;
import pl.clinic.security.rest.JwtUtils;
import pl.clinic.security.rest.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserService userService;

  @Autowired
  RoleService roleService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

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
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userService.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    User buildUser = User.builder()
            .username(signUpRequest.getUsername())
            .email(signUpRequest.getEmail())
            .active(true)
            .password(encoder.encode(signUpRequest.getPassword()))
            .build();


    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleService.findByName("PATIENT");
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "DOCTOR":
          Role doctorRole = roleService.findByName("DOCTOR");
          roles.add(doctorRole);

          break;
        case "ADMIN":
          Role adminRole = roleService.findByName("ADMIN");
               roles.add(adminRole);
          break;
        default:
          Role patientRole = roleService.findByName("PATIENT");
          roles.add(patientRole);
        }
      });
    }

    User user = buildUser.withRoles(roles);
    userService.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}