package com.example.sample_spirng_data_jpa.repository

import com.example.sample_spirng_data_jpa.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * JpaRepository
 *  PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>インターフェースを継承したインターフェース
 */
@Repository
interface EmployeeRepository: JpaRepository<Employee,Int> {



}