package com.example.sample_spring_data_jpa.restapi

import com.example.sample_spring_data_jpa.domain.Employee
import com.example.sample_spring_data_jpa.model.EmployeeDto
import com.example.sample_spring_data_jpa.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/employee")
class EmployeeRestCotroller(private val employeeService: EmployeeService){

    @GetMapping(path = ["/all"])
    internal fun getAllCustomers(): List<EmployeeDto> = employeeService.findAll()

    @GetMapping(path = ["/one"])
    internal fun getOne(id:Int): EmployeeDto = employeeService.getOne(id)

    @GetMapping(path = ["/all/firstname"])
    internal fun getAllCustomersSortFirstName(): List<EmployeeDto> = employeeService.findAllSortFirstName()

    @GetMapping(path = ["/count"])
    internal fun count(): Int = employeeService.count()
}