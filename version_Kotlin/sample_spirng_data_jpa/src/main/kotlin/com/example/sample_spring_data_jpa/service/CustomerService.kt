package com.example.sample_spring_data_jpa.service

import com.example.sample_spring_data_jpa.domain.Customer
import com.example.sample_spring_data_jpa.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun findAll(): List<Customer> = customerRepository.findAllOrderByName()

    fun findAll(pageable: Pageable): Page<Customer> = customerRepository.findAllOrderByName(pageable)

    fun findOne(id: Int?): Customer = customerRepository.findOne(id)

    fun create(customer: Customer): Customer = customerRepository.save(customer)

    fun update(customer: Customer): Customer = customerRepository.save(customer)

    fun delete(id: Int?) = customerRepository.delete(id)

    fun deleteAll() = customerRepository.deleteAll()
}
