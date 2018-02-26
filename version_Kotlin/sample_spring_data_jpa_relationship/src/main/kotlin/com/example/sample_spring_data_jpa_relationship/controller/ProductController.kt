package com.example.sample_spring_data_jpa_relationship.controller

import com.example.sample_spring_data_jpa_relationship.Service.ProductService
import com.example.sample_spring_data_jpa_relationship.domain.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("api")
class ProductController(private val productService:ProductService) {


//    @GetMapping(path = ["/employee/all"])
//    internal fun getAllEmployee(): MutableList<Company>? = productService.getCompanyList()
//
//    @GetMapping(path = ["/employee/{no}"])
//    internal fun getEmployee(@PathVariable no:Int): Employee = employeeService.getemployeement(no)
//
//    @GetMapping(path = ["/department/all"])
//    internal fun getAllDepartment(): MutableList<Department>? = employeeService.getdepartmentList()
//
//    @GetMapping(path = ["/department/{no}"])
//    internal fun getDepartment(@PathVariable no:Int): Department = employeeService.getdepartment(no)
//
    @PostMapping("/save/1")
    fun save1(@RequestBody productDto: ProductDto, uriBuilder: UriComponentsBuilder): ResponseEntity<String>? {

        productService.saveProduct(productDto)
        return ResponseEntity.ok().build<String>()
    }

    @PostMapping("/save/2")
    fun save2(@RequestBody companyDto: CompanyDto, uriBuilder: UriComponentsBuilder): ResponseEntity<String>? {

        productService.saveCompany(companyDto)
        return ResponseEntity.ok().build<String>()
    }

}