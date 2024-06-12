package com.example.svtkvtproject.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;
    private String description;

    @ElementCollection
    private List<String> disciplines;

    @ElementCollection
    private List<Integer> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WorkingTime> workingTimes = new ArrayList<>();

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDisciplines() {
        return disciplines;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public List<WorkingTime> getWorkingTimes() {
        return workingTimes;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisciplines(List<String> disciplines) {
        this.disciplines = disciplines;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Integer rating) {
        this.ratings.add(rating);
    }

    public void setWorkingTimes(List<WorkingTime> workingTimes) {
        this.workingTimes = workingTimes;
        for (WorkingTime workingTime : workingTimes) {
            workingTime.setGym(this); // Associate WorkingTime with the current Gym
        }
    }
}
