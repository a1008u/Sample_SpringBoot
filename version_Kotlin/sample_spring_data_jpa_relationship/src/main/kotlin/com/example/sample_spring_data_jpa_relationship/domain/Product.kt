package com.example.sample_spring_data_jpa_relationship.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity @Table(name = "product")
data class Product(
    var name: String = "",

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    var company: Company? = null,
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0
){
	override fun toString(): String{
		return "{name: ${name}, company: ${company?.name}}"	
	}
}

data class ProductDto(
		var id: Long = 0,
		var name:String = "unregisterd",
		var company: Company = Company()
){
	companion object {
		fun fromDto(dto: ProductDto) = Product(
				dto.name
				, dto.company
				, dto.id
		)
		fun toDto(department: Product) = ProductDto(
				department.id
				, department.name
				, department.company!!
		)
	}
}