package com.example.sample_spirng_data_jpa.model

import com.example.sample_spirng_data_jpa.domain.Sex
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import java.sql.Date
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.NamedQuery
import javax.validation.constraints.Digits



data class EmployeeForm(

        var firstName:String = "test"
        ,var lastName:String = "test"
        ,@Enumerated(EnumType.STRING) var sex: Sex = Sex.male
        ,@Length(max = 100) var tel: String = "998887777"
        ,var birthday: Date =  Date.valueOf("0001-01-01")
        ,var mailAddress:String = "test@test"
        ,@Digits(integer = 10, fraction = 2) var salary: BigDecimal = 999.99.toBigDecimal()
)

