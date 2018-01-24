package com.example.sample_9_security.repository;

import com.example.sample_9_security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> { }