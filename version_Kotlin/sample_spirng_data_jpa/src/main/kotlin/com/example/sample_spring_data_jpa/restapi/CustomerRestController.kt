package com.example.sample_spring_data_jpa.restapi


import com.example.sample_spring_data_jpa.domain.Customer
import com.example.sample_spring_data_jpa.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("api/customers")
class CustomerRestController(private val customerService: CustomerService) {

    @GetMapping
    internal fun getCustomers(): List<Customer> = customerService.findAll()

    @GetMapping(path = arrayOf("/page"))
    internal fun getCustomers(@PageableDefault pageable: Pageable): Page<Customer> = customerService.findAll(pageable)

    @GetMapping(path = arrayOf("{id}"))
    internal fun getCustomer(@PathVariable id: Int?): Customer = customerService.findOne(id)

    @PostMapping
    internal fun postCustomers(@RequestBody customer: Customer, uriBuilder: UriComponentsBuilder): ResponseEntity<Customer> {
        val created = customerService.create(customer)
        val location = uriBuilder.path("api/customers/{id}")
                .buildAndExpand(created.id)
                .toUri()

        return ResponseEntity.created(location).body(created)
    }

    @PutMapping(path = ["{id}"])
    internal fun putCustomer(@PathVariable id: Int?, @RequestBody customer: Customer): Customer {

        customer.id = requireNotNull(id)
        return customerService.update(customer)
    }

    @DeleteMapping(path = ["{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    internal fun deleteCustomer(@PathVariable id: Int?) = customerService.delete(id)

}