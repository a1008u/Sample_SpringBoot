package com.example.reactcassandra.controller

import com.example.react.model.Employee
import com.example.react.model.EmployeeUpdateReq
import com.example.react.service.DepartmentService
import com.example.react.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class EmployeeController(private val employeeService: EmployeeService,
                         private val departmentService: DepartmentService){

    @GetMapping("/departments")
    fun getAllDepartment() = departmentService.getAllDepartments()

    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable id: Int) = employeeService.getEmployee(id)

    @GetMapping("/employee")
    fun getAllEmployees(@RequestParam("minAge", required = false)minAge: Int?,
                        @RequestParam("minSalary", required = false) minSalary: Double?)
            = employeeService.getAllEmployees(minAge, minSalary)

    @PostMapping("/employee/{id}")
    fun updateEmployee(@PathVariable id: Int,
                       @RequestBody updateEmployee: EmployeeUpdateReq)
            = employeeService.updateEmployee(id, updateEmployee)

    @PostMapping("/employee")
    fun createEmployee(@RequestBody employee:Employee) : ResponseEntity<String> {
        employeeService.createEmployee(employee)
        return ResponseEntity.status(HttpStatus.CREATED).build<String>()
    }

    @DeleteMapping("/employee/{id}")
    fun deleteEmployee(@PathVariable id: Int): ResponseEntity<String> {
        employeeService.deleteEmployee(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build<String>()
    }
}
