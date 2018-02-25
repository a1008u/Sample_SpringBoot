package com.example.sample_spring_data_jpa_relationship.controller

import com.example.sample_spring_data_jpa_relationship.Service.EmployeeService
import com.example.sample_spring_data_jpa_relationship.domain.Department
import com.example.sample_spring_data_jpa_relationship.domain.DepartmentDto
import com.example.sample_spring_data_jpa_relationship.domain.Employee
import com.example.sample_spring_data_jpa_relationship.domain.EmployeeDto
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.text.SimpleDateFormat

@RestController
@RequestMapping("api")
class EmployeeController(private val employeeService: EmployeeService) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.isLenient = false
        binder.registerCustomEditor(java.sql.Date::class.java, CustomDateEditor(dateFormat, true))
    }

    // 検索 ------------------------------

    @GetMapping(path = ["/employee/all"])
    internal fun getAllEmployee(): MutableList<Employee>? = employeeService.getemployeementList()

    @GetMapping(path = ["/employee/{no}"])
    internal fun getEmployee(@PathVariable no:Int): Employee = employeeService.getemployeement(no)

    @GetMapping(path = ["/department/all"])
    internal fun getAllDepartment(): MutableList<Department>? = employeeService.getdepartmentList()

    @GetMapping(path = ["/department/{no}"])
    internal fun getDepartment(@PathVariable no:Int): Department= employeeService.getdepartment(no)

    // 挿入 ------------------------------

    @PostMapping(path = ["/employee/insert"])
    fun insert(@RequestBody employeeDto: EmployeeDto, uriBuilder: UriComponentsBuilder): ResponseEntity<String>? {

//        employeeService.getdepartment(employeeDto.departmentCode).apply {
//            employeeDto.departmentCode = code
//            employeeDto.departmentName = name
//        }
        val created = employeeService.saveEmployee(employeeDto)
        return ResponseEntity.ok().build<String>()
//        val location = uriBuilder.path("api/employee/{no}").buildAndExpand(created.no).toUri()
//        return ResponseEntity.created(location).body(created)
    }

    // 更新 ------------------------------
    @PutMapping(path = ["/employee/update"])
    fun update(@RequestBody employeeDto: EmployeeDto, uriBuilder: UriComponentsBuilder): ResponseEntity<String>? {
        val empList = employeeService.getemployeementList()
        val created = employeeService.updateEmployee(employeeDto.firstName, empList)
        return if (created != 0 ) ResponseEntity.ok().build<String>() else ResponseEntity.status(HttpStatus.NOT_MODIFIED).build<String>()
    }

    // 削除 ------------------------------

    @DeleteMapping(path = ["/department/{no}"])
    fun delete(@PathVariable no:Int) = employeeService.deleteEmployee(no)
}