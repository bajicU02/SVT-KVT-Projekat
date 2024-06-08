package com.example.svtkvtproject.Entities.Repositories;

import com.example.svtkvtproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
