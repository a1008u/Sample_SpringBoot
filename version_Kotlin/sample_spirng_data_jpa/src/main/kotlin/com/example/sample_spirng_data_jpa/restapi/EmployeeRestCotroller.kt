package com.example.sample_spirng_data_jpa.restapi

import com.example.sample_spirng_data_jpa.domain.Customer
import com.example.sample_spirng_data_jpa.domain.Employee
import com.example.sample_spirng_data_jpa.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/employee")
class EmployeeRestCotroller(private val employeeService: EmployeeService){

    @GetMapping(path = ["/all"])
    internal fun getAllCustomers(): List<Employee> = employeeService.findAll()

    @GetMapping(path = ["/all/firstname"])
    internal fun getAllCustomersSortFirstName(): List<Employee> = employeeService.findAllSortFirstName()
}