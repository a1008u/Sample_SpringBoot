package com.example.react.service

import com.example.react.model.Employee
import com.example.react.model.EmployeeUpdateReq
import org.springframework.stereotype.Service
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono


@Service
class EmployeeService() {

    companion object {
        val employeedb = mutableMapOf(1 to Employee(1,"tom",32,"Engineering",300.00),
                2 to Employee(2,"sam",22,"HR",10.00))
    }

    fun createEmployee(employee: Employee) = employeedb.put(employee.id, employee)

    fun getEmployee(id: Int) = employeedb[id].toMono()

    fun getAllEmployees(minAge:Int? = null , minSalary: Double? = null)
            = requireNotNull(employeedb.values.toFlux()
                                .filter { it.age >= minAge ?: Int.MIN_VALUE }
                                .filter { it.salary >= minSalary ?: Double.MIN_VALUE })

    fun updateEmployee(id:Int, updateEmployee: EmployeeUpdateReq){
        val employeeOndb = requireNotNull(employeedb[id])
        employeedb[id] = employeeOndb.run { Employee(id
                                                    , name
                                                    , age
                                                    , if (updateEmployee.department.isEmpty()) department else updateEmployee.department
                                                    , if (updateEmployee.salary == 0.0) salary else updateEmployee.salary ) }
    }

    fun deleteEmployee(id: Int) = employeedb.remove(id)
}