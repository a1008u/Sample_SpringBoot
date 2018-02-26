package com.example.sample_spring_data_jpa.service


import com.example.sample_spring_data_jpa.domain.Sex
import com.example.sample_spring_data_jpa.model.EmployeeDto
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Assert.assertThat
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
    fun count引数ありのテスト(){}

    @Test
    fun findAllExample(){}

    @Test
    fun readByFirstNameのテスト(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.readByFirstName(employeeDto)
        assertThat(empDtoList.size, `is`(2))
    }

    @Test
    fun readByFirstNameAndSexのテスト(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.readByFirstNameAndSex(employeeDto)
        assertThat(empDtoList.size, `is`(2))
    }

    @Test
    fun readByBirthdayGreaterThan(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.readByBirthdayGreaterThan(employeeDto)
        assertThat(empDtoList.size, `is`(15))
    }

    @Test
    fun getByFirstName(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.getByFirstName(employeeDto)
        assertThat(empDtoList.size, `is`(2))
    }

    @Test
    fun queryByFirstNameのテスト(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.queryByFirstName(employeeDto)
        assertThat(empDtoList.size, `is`(2))
    }

    @Test
    fun getFirstByFirstNameのテスト(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val employeeInterfaceList =  employeeService.getFirstByFirstName1(employeeDto)
        assertThat(employeeInterfaceList.getFirst(), `is`("Nobita"))
    }

    // update ----------------------------------

    @Test
    fun updateFirstNameのテスト(){
        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val empDtoList =  employeeService.readByFirstName(employeeDto)

        val updateInt = employeeService.updateFirstName("tom", empDtoList)
        assertThat(updateInt, `is`(3))

        val employeeDto2 = EmployeeDto(1,"tom","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
        val changeNameList = employeeService.readByFirstName(employeeDto2)
        assertThat(changeNameList.size,`is`(3))
    }

    // save -------------------------------------

    @Test
    fun saveEmployeeのテスト(){
        val employeeDto = EmployeeDto(13333,"NobitaZ","NobiZ",Sex.female, "08011111212", Date.valueOf("1999-03-21"),"NobiZ@mail.com",999999.34.toBigDecimal())
        val EmployeeDto =  employeeService.saveEmployee(employeeDto)
        val restultEmployeeList = employeeService.readByFirstName(EmployeeDto)
        assertThat(restultEmployeeList.size,`is`(1))

        restultEmployeeList.forEach { e ->
            assertThat(e.id,`is`(not(13333)))
            assertThat(e.firstName,`is`("NobitaZ"))
        }
    }





//    未解決
//    @Test
//    fun findAll引数ありのテスト() {
//        val employeeDto = EmployeeDto(1,"Nobita","Nobi",Sex.male, "08011112222", Date.valueOf("1980-03-21"),"Nobi@mail.com",100.34.toBigDecimal())
//        val empDtoList =  employeeService.findAll(employeeDto)
//        assertThat(empDtoList.size, `is`(1))
//    }


}
