package com.example.sample_spring_data_jpa_relationship.domain
import com.example.sample_spring_data_jpa_relationship.domain.Department
import com.example.sample_spring_data_jpa_relationship.domain.Employee
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity @Table(name = "company")
data class Company(
    var name: String = "",
    
    @OneToMany(mappedBy = "company", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
    var products: List<Product> = emptyList(),
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0
){
	override fun toString(): String{
		return "{name: ${this.name}, products: ${products.map { it->it.name }}}";
	}
}

data class CompanyDto(
		var id: Long = 0,
		var name:String = "unregisterd",
		var products: List<Product> = arrayListOf()
){
	companion object {
		fun fromDto(dto: CompanyDto) = Company(
				dto.name
				, dto.products
				,dto.id
		)
		fun toDto(department: Company) = CompanyDto(
				department.id
				,department.name
				, department.products
		)
	}
}