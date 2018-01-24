package com.example.sample_9_security.repository;

import com.example.sample_9_security.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    List<Customer> findAllOrderByName();

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    Page<Customer> findAllOrderByName(Pageable pageable);
}

