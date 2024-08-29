package codeVibe.task.controller;

import codeVibe.task.model.Users;
import codeVibe.task.dtos.AuthenticationRequest;
import codeVibe.task.dtos.AuthenticationResponse;
import codeVibe.task.dtos.RegistrationRequest;
import codeVibe.task.service.UserService;
import codeVibe.task.config.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(@RequestParam(value = "sortBy", required = false) String sortBy, HttpServletRequest request) {
        if (!validateJwt(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        List<Users> users = userService.getAllUsers(sortBy);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id, HttpServletRequest request) {
        if (!validateJwt(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Users user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user, HttpServletRequest request) {
        if (!validateJwt(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Users createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user, HttpServletRequest request) {
        if (!validateJwt(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Users updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        if (!validateJwt(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        Users newUser = new Users();
        newUser.setUsername(registrationRequest.getUsername());
        newUser.setPassword(registrationRequest.getPassword());
        newUser.setEmail(registrationRequest.getEmail());

        Users createdUser = userService.createUser(newUser);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(registrationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private boolean validateJwt(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String jwtToken = null;
        String username = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            }
        } else {
            System.out.println("JWT Token does not begin with Bearer String");
        }

        if (username != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            return jwtTokenUtil.validateToken(jwtToken, userDetails);
        }

        return false;
    }
}
