package com.example.sample_spirng_data_jpa.model

import org.springframework.beans.factory.annotation.Value

interface EmployeeInterface {

    @Value("#{target.firstName}")
    fun getFirst()

    @Value("#{target.lastName}")
    fun getLast()

}