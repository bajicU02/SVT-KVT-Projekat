package com.example.svtkvtproject.Entities.Repositories;

import com.example.svtkvtproject.Entities.WorkingTime;
import com.example.svtkvtproject.Entities.Repositories.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/working-times")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from the Angular app
public class WorkingTimeController {

    @Autowired
    private WorkingTimeRepository workingTimeRepository;

    // Get all working times
    @GetMapping
    public List<WorkingTime> getAllWorkingTimes() {
        return workingTimeRepository.findAll();
    }

    // Get working time by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkingTime> getWorkingTimeById(@PathVariable Long id) {
        return workingTimeRepository.findById(id)
                .map(workingTime -> ResponseEntity.ok().body(workingTime))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new working time
    @PostMapping
    public WorkingTime createWorkingTime(@RequestBody WorkingTime workingTime) {
        return workingTimeRepository.save(workingTime);
    }

    // Update an existing working time
    @PutMapping("/{id}")
    public ResponseEntity<WorkingTime> updateWorkingTime(@PathVariable Long id, @RequestBody WorkingTime workingTimeDetails) {
        return workingTimeRepository.findById(id)
                .map(workingTime -> {
                    workingTime.setDayOfWeek(workingTimeDetails.getDayOfWeek());
                    workingTime.setOpeningTime(workingTimeDetails.getOpeningTime());
                    workingTime.setClosingTime(workingTimeDetails.getClosingTime());
                    workingTime.setGym(workingTimeDetails.getGym());
                    WorkingTime updatedWorkingTime = workingTimeRepository.save(workingTime);
                    return ResponseEntity.ok().body(updatedWorkingTime);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a working time
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkingTime(@PathVariable Long id) {
        return workingTimeRepository.findById(id)
                .map(workingTime -> {
                    workingTimeRepository.delete(workingTime);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
