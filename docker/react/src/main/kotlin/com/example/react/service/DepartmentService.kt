package com.example.react.service

import org.springframework.stereotype.Service

@Service
class DepartmentService(private val employeeService: EmployeeService){

    fun getAllDepartments() = requireNotNull(employeeService.getAllEmployees()
                                                            .map { it.department }
                                                            .distinct())
}