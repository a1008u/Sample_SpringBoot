package com.example.sample_layering.service;

import com.example.sample_layering.domain.Customer;
import com.example.sample_layering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    /**
     * データを保存する (現状はDBに接続させていない)
     * @param customer
     * @return customerRepository.save(customer)
     */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * データを全て取得 (現状はDBに接続させていない)
     * @return
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
