package com.techprimers.jpa.springdatajpahibernateexample.service;


import com.techprimers.jpa.springdatajpahibernateexample.domain.Customer;
import com.techprimers.jpa.springdatajpahibernateexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() { return customerRepository.findAllOrderByName(); }

    public Page<Customer> findAll(Pageable pageable) { return customerRepository.findAllOrderByName(pageable); }

    public Customer findOne(Integer id) {
        return customerRepository.findOne(id);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Integer id) {
        customerRepository.delete(id);
    }
}
