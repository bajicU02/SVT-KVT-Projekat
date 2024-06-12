package com.example.svtkvtproject.Entities.Repositories;

import com.example.svtkvtproject.Entities.Gym;
import com.example.svtkvtproject.Entities.Repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gyms")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from the Angular app
public class GymController {

    @Autowired
    private GymRepository gymRepository;

    // Get all gyms
    @GetMapping
    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    // Get gym by ID
    @GetMapping("/{id}")
    public ResponseEntity<Gym> getGymById(@PathVariable Long id) {
        return gymRepository.findById(id)
                .map(gym -> ResponseEntity.ok().body(gym))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new gym
    @PostMapping
    public Gym createGym(@RequestBody Gym gym) {
        return gymRepository.save(gym);
    }

    // Update an existing gym
    @PutMapping("/{id}")
    public ResponseEntity<Gym> updateGym(@PathVariable Long id, @RequestBody Gym gymDetails) {
        return gymRepository.findById(id)
                .map(gym -> {
                    gym.setName(gymDetails.getName());
                    gym.setCity(gymDetails.getCity());
                    gym.setAddress(gymDetails.getAddress());
                    gym.setDescription(gymDetails.getDescription());
                    gym.setDisciplines(gymDetails.getDisciplines());
                    gym.setWorkingTimes(gymDetails.getWorkingTimes());
                    Gym updatedGym = gymRepository.save(gym);
                    return ResponseEntity.ok().body(updatedGym);
                }).orElse(ResponseEntity.notFound().build());
    }

 // Delete a gym
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGym(@PathVariable Long id) {
        return gymRepository.findById(id)
                .map(gym -> {
                    gymRepository.delete(gym);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    // Add a rating to a gym
    @PostMapping("/{id}/ratings")
    public ResponseEntity<Gym> addRating(@PathVariable Long id, @RequestBody Integer rating) {
        return gymRepository.findById(id)
                .map(gym -> {
                    gym.addRating(rating);
                    Gym updatedGym = gymRepository.save(gym);
                    return ResponseEntity.ok().body(updatedGym);
                }).orElse(ResponseEntity.notFound().build());
    }
}
