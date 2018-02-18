package com.example.sample_spring_data_jpa.repository

import com.example.sample_spring_data_jpa.domain.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Int>{

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    fun findAllOrderByName(): List<Customer>

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    fun findAllOrderByName(pageable: Pageable): Page<Customer>
}