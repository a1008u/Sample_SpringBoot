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
    internal fun getAllEmployee(): List<EmployeeDto> = employeeService.getemployeementList()

    @GetMapping(path = ["/employee/{no}"])
    internal fun getEmployee(@PathVariable no:Int): EmployeeDto = employeeService.getemployeement(no)

    @GetMapping(path = ["/department/all"])
    internal fun getAllDepartment(): List<DepartmentDto> = employeeService.getdepartmentList()

    @GetMapping(path = ["/department/{no}"])
    internal fun getDepartment(@PathVariable no:Int): DepartmentDto= employeeService.getdepartment(no)

    // 挿入 ------------------------------
    @PostMapping(path = ["/employee/insert"])
    fun insertEmployee(@RequestBody employeeDto: EmployeeDto, uriBuilder: UriComponentsBuilder): ResponseEntity<Employee> {
        val created = employeeService.saveEmployee(employeeDto)
        val location = requireNotNull(uriBuilder.path("api/employee/{no}").buildAndExpand(created.no).toUri())
        return ResponseEntity.created(location).body(created)
    }


    @PostMapping(path = ["/department/insert"])
    fun insertDepartment(@RequestBody departmentDto: DepartmentDto, uriBuilder: UriComponentsBuilder): ResponseEntity<Department> {
        val created = employeeService.saveDeparatment(departmentDto)
        val location = requireNotNull(uriBuilder.path("api/department/{no}").buildAndExpand(created.code).toUri())
        return ResponseEntity.created(location).body(created)
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