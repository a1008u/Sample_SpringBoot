package com.example.sample_spring_data_jpa_relationship.repository

import com.example.sample_spring_data_jpa_relationship.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Int>, JpaSpecificationExecutor<Employee> {
    @Modifying
    @Query("DELETE FROM Employee WHERE no IN (:nos)")
    fun deleteEmployeeById(@Param("nos") nos: Set<Int>): Int


    @Modifying(clearAutomatically = true)
    @Query("UPDATE employee SET first_name = :targetFirstName WHERE first_name = :firstName",nativeQuery = true)
    fun updateFirstName(@Param("targetFirstName") targetFirstName: String
                        , @Param("firstName") firstName: String):Int
}