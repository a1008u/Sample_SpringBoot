package com.example.reactcassandra.repository


import com.example.reactcassandra.model.Employee
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import org.springframework.stereotype.Repository

// ReactiveCassandraRepository<T, ID>
@Repository
interface EmployeeRepository: ReactiveCassandraRepository<Employee, String>