package com.example.sample_spring_data_jpa.model

import org.springframework.beans.factory.annotation.Value

interface EmployeeInterface {

    @Value("#{target.firstName}")
    fun getFirst() : String

    @Value("#{target.lastName}")
    fun getLast()

}