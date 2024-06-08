package com.example.svtkvtproject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    
    //Constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public User() {

    }
    
    //Getters and Setters
    public Long getId() {
        return id;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name2) {
		this.name = name2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email2) {
		this.email = email2;		
	}

	
}