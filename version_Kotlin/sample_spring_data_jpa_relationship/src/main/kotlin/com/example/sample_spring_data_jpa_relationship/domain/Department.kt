package com.example.sample_spring_data_jpa_relationship.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import java.sql.Date
import javax.persistence.*
import javax.validation.constraints.Digits

/**
 * @OneToMany
 * 一対多の関係を定義する
 *  optional
 *  mappedBy
 *  fetch
 *  cascade
 *
 * @JoinColumn
 *  所有者側のエンティティに指定してリレーションシップを定義する
 *   name
 *   referencedColumnName
 *   nullable
 *   unique
 *   foreignKey
 *
 *
 * 所有者側　：Employeeクラス
 * 被所有者側：Departmentクラス
 *
 */

@Entity
@Table(name = "department")
data class Department(
        @Id
        @GeneratedValue
        val code:Int=0,

        val name:String = "unregisterd",

        @OneToMany(mappedBy = "department", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @OrderBy("firstName, birthday DESC")
        @JsonBackReference // 無限ループを避けるため、こちらは双方向のEmployeeを表示させない
        var employee: List<Employee> = emptyList()
){
        override fun toString(): String{
                return "{student: ${this.name}}}"
        }
}

data class DepartmentDto(
        val code: Int = 0,
        val name:String = "unregisterd",
        val empList: List<Employee> = emptyList()
){
        companion object {

                fun fromDto2(dto: EmployeeDto) = Department(
                        dto.departmentCode
                        , dto.departmentName
                )

                fun fromDto(dto: DepartmentDto) = Department(
                        dto.code
                        , dto.name
                        , dto.empList
                        )

                fun toDto(department: Department) = DepartmentDto(
                        department.code
                        , department.name
                        , department.employee
                        )

        }
}
