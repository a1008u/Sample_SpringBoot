package com.example.sample_spring_data_jpa.model

import com.example.sample_spring_data_jpa.domain.Employee
import com.example.sample_spring_data_jpa.domain.Sex
import org.hibernate.validator.constraints.Length
import org.springframework.beans.factory.annotation.Value
import java.math.BigDecimal
import java.sql.Date
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Digits



data class EmployeeDto(
        var id:Int = 0,
        var firstName:String = "test"
        ,var lastName:String = "test"
        ,@Enumerated(EnumType.STRING) var sex: Sex = Sex.male
        ,@Length(max = 100) var tel: String = "998887777"
        ,var birthday: Date =  Date.valueOf("0001-01-01")
        ,var mailAddress:String = "test@test"
        ,@Digits(integer = 10, fraction = 2) var salary: BigDecimal = 999.99.toBigDecimal()
){
    companion object {
        fun fromDto(dto: EmployeeDto) = Employee(Int.MAX_VALUE
                , dto.firstName
                , dto.lastName
                , dto.sex
                , dto.tel
                , dto.birthday
                , dto.mailAddress
                , dto.salary)
        fun toDto(employee: Employee) = EmployeeDto(employee.id
                , employee.firstName
                , employee.lastName
                , employee.sex
                , employee.tel
                , employee.birthday
                , employee.mailAddress
                , employee.salary)
    }
}



interface EmployeeDtoInterface {

    @Value("#{target.id}")
    fun getId()

    @Value("#{target.firstName}")
    fun getFirstName()

    @Value("#{target.lastName}")
    fun getLastName()

}
