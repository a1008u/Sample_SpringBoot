package com.example.sample_spring_data_jpa_relationship.repository

import com.example.sample_spring_data_jpa_relationship.domain.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long>{
}