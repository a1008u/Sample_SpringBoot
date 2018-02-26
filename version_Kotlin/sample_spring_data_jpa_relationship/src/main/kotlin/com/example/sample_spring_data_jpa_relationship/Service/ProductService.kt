package com.example.sample_spring_data_jpa_relationship.Service

import com.example.sample_spring_data_jpa_relationship.domain.*
import com.example.sample_spring_data_jpa_relationship.repository.CompanyRepository
import com.example.sample_spring_data_jpa_relationship.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(
        private val companyRepository: CompanyRepository
        , private val productRepository: ProductRepository) {

    fun getCompanyList() = requireNotNull(companyRepository.findAll())
    fun getProductList() = requireNotNull(productRepository.findAll())

    fun geteCompany(id: Long) = requireNotNull(companyRepository.findOne(id))
    fun getProduct(id: Long) = requireNotNull(productRepository.findOne(id))


    @Transactional
    fun saveCompany(companyDto: CompanyDto): Company {

        val company = Company(companyDto.name)
        val pList = companyDto.products.map { c -> Product(c.name, company) }.toList()
        company.products = pList

        return companyRepository.save(company)
    }

    @Transactional
    fun saveProduct(productdto: ProductDto): Product {

        // prepare Company data
        val company = Company(productdto.company.name)
        companyRepository.save(company)

        // prepare Product data
        val product = Product(productdto.name, company)
        return productRepository.save(product)
    }
}