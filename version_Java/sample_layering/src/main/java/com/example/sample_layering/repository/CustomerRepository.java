package com.example.sample_layering.repository;

import com.example.sample_layering.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class CustomerRepository {
    private final ConcurrentMap<Integer, Customer> customerMap = new ConcurrentHashMap<>();

    /**
     * CRUDのRに相当する
     * @return　 ArrayList<>(customerMap.values())
     */
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    /**
     * CRUDのRに相当する
     * @return　 customerMap.get(customerId)
     */
    public Customer findOne(Integer customerId) {
        return customerMap.get(customerId);
    }

    /**
     * CRUDのCに相当する
     * @return　 customerMap.put(customer.getId(), customer)
     */
    public Customer save(Customer customer) {
        return customerMap.put(customer.getId(), customer);
    }

    /**
     * CRUDのDに相当する
     */
    public void delete(Integer customerId) {
        customerMap.remove(customerId);
    }
}
