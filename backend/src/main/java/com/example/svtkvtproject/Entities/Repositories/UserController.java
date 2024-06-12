package com.example.svtkvtproject.Entities.Repositories;

import com.example.svtkvtproject.Entities.User;
import com.example.svtkvtproject.Entities.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.svtkvtproject.Entities.DataTransferObjects.LoginRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from the Angular app
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setSurname(userDetails.getSurname());
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    user.setBirthday(userDetails.getBirthday());
                    user.setPhoneNumber(userDetails.getPhoneNumber());
                    user.setAddress(userDetails.getAddress());
                    user.setCity(userDetails.getCity());
                    user.setZipCode(userDetails.getZipCode());
                    user.setUserType(userDetails.getUserType());
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok().body(updatedUser);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // User login method
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for email: " + loginRequest.getEmail());

        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            logger.info("User found: " + user.getEmail());
            logger.info("Stored password: " + user.getPassword());
            logger.info("Provided password: " + loginRequest.getPassword());
            if (user.getPassword().equals(loginRequest.getPassword())) {
                logger.info("Login successful for user: " + user.getEmail());
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("userType", user.getUserType().toString()); // Ensure userType is included in the response
                return ResponseEntity.ok(response);
            } else {
                logger.warn("Invalid password for user: " + user.getEmail());
                Map<String, String> response = new HashMap<>();
                response.put("message", "Invalid password");
                return ResponseEntity.status(401).body(response);
            }
        } else {
            logger.warn("User not found with email: " + loginRequest.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }
    
}
