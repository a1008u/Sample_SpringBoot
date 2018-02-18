package com.example.sample_spring_data_jpa.service


import com.example.sample_spring_data_jpa.domain.Sex
import com.example.sample_spring_data_jpa.model.EmployeeDto
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Date


@Sql
@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EmployeeServiceTest{

    @Autowired
    lateinit var employeeService: EmployeeService

    companion object {
        const val firstNameNobita = "Nobita"
        const val firstNameTakeshi= "Takeshi"
        const val firstNameSuneo = "Suneo"
        val birthday20000101: Date = Date.valueOf("2000-01-01")
        val birthday19810430: Date = Date.valueOf("1981-04-30")
        val birthday19800321 = Date.valueOf("1980-03-21")
        val birthday20000321 = Date.valueOf("2000-03-21")
        val birthday19800430 = Date.valueOf("1980-04-30")
        val birthday20000430 = Date.valueOf("2000-04-30")
        val birthday20100430 = Date.valueOf("2010-04-30")
    }

    @After
    fun delete(){
        employeeService.deleteAll()
    }

    @Test
    fun getOneのテスト() {
        val empDtoList =  employeeService.findAll()
        val empDto =  employeeService.getOne(empDtoList.first().id)
        assertThat(empDto.firstName, `is`("Nobita"))
    }

    @Test
    fun findAllのテスト() {
        val empDtoList =  employeeService.findAll()
        assertThat(empDtoList.size, `is`(17))
    }

    @Test
    fun findAllSortFirstNameのテスト() {
        val empDtoList =  employeeService.findAllSortFirstName()
        assertThat(empDtoList.size, `is`(17))
    }

    @Test
    fun findAllSortFirstName2のテスト() {
        val empDtoList = employeeService.findAllSortFirstName2()
        assertThat(empDtoList.size, `is`(17))
    }

    @Test
    fun countのテスト(){
        val empInt = employeeService.count()
        assertThat(empInt, `is`(17))
    }

    @Test
    fun readByFirstNameのテスト(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.readByFirstName(employeeDto)
        assertThat(empDtoList.size, `is`(2))
    }

//    未解決
//    @Test
//    fun findAll引数ありのテスト() {
//        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
//        val empDtoList =  employeeService.findAll(employeeDto)
//        assertThat(empDtoList.size, `is`(1))
//    }


}
