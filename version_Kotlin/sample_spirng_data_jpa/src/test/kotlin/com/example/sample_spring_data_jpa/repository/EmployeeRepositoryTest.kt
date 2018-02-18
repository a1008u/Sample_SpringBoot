package com.example.sample_spring_data_jpa.repository

import com.example.sample_spring_data_jpa.domain.Sex
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Date
import java.util.concurrent.Future

@RunWith(SpringRunner::class)
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
@Sql
class EmployeeRepositoryTest {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

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

    @Test
    fun readByFirstNameのテスト() {
        val empList = employeeRepository.readByFirstName(firstNameNobita)
        assertThat(empList.size, `is`(2))
    }

    @Test
    fun readByFirstNameIgnoringCaseのテスト() {
        val empList = employeeRepository.readByFirstNameIgnoringCase(firstNameNobita)
        assertThat(empList.size, `is`(2))
    }

    @Test
    fun readByFirstNameAndSexのテスト() {
        val empList = employeeRepository.readByFirstNameAndSex(firstNameNobita, Sex.male)
        assertThat(empList.size, `is`(2))
    }

    @Test
    fun readByBirthdayGreaterThanのテスト() {
        val empList = employeeRepository.readByBirthdayGreaterThan(birthday20000101)
        assertThat(empList.size, `is`(4))
    }

    @Test
    fun readByFirstNameOrderByBirthdayのテスト() {
        val empList = employeeRepository.readByFirstNameOrderByBirthday(firstNameNobita)
        assertThat(empList.size, `is`(2))
        empList
                .map { e-> e.birthday }
                .toTypedArray()
                .run { assertThat(
                        this
                        , arrayContainingInAnyOrder(birthday19800321, birthday20000321))
        }
    }
    @Test
    fun readByFirstNameOrderByBirthdayDescのテスト() {
        val empList = employeeRepository.readByFirstNameOrderByBirthdayDesc(firstNameNobita)
        assertThat(empList.size, `is`(2))
        empList
                .map { e-> e.birthday }
                .toTypedArray()
                .run { assertThat(
                        this
                        , arrayContainingInAnyOrder(birthday20000321, birthday19800321))
                }
    }

    @Test
    fun readByFirstNameOrderByBirthdayDescSexのテスト() {
        val empList = employeeRepository.readByFirstNameOrderByBirthdayDescSex(firstNameTakeshi)
        assertThat(empList.size, `is`(3))
        empList
                .map { e-> e.birthday }
                .toTypedArray()
                .run { assertThat(
                        this
                        , arrayContainingInAnyOrder(birthday20100430, birthday20000430, birthday19800430))
                }
        empList
                .map { e-> e.sex }
                .toTypedArray()
                .run { assertThat(
                        this
                        , arrayContainingInAnyOrder(Sex.female,Sex.male,Sex.male))
                }
    }

    @Test
    fun readFirstByFirstNameのテスト() {
        val empList = employeeRepository.readFirstByFirstName(firstNameTakeshi)
        assertThat(empList.tel, `is`("08022223333"))
    }

    @Test
    fun readTop1ByFirstNameのテスト() {
        val empList = employeeRepository.readTop1ByFirstName(firstNameTakeshi)
        assertThat(empList.tel, `is`("08022223333"))
    }

    @Test
    fun readFirst10ByFirstNameのテスト() {
        val empList = employeeRepository.readFirst10ByFirstName(firstNameSuneo)
        assertThat(empList.size, `is`(10))
        assertThat(empList.size, `is`(not(11)))
        empList
                .map { e-> e.mailAddress }
                .toTypedArray()
                .run { assertThat(
                        this.toString()
                        , `is`(not(containsString("Honekawa11@mail.com"))))
                }
    }

    @Test
    fun readTop10ByFirstNameのテスト() {
        val empList = employeeRepository.readTop10ByFirstName(firstNameSuneo)
        assertThat(empList.size, `is`(10))
        assertThat(empList.size, `is`(not(11)))
        empList
                .map { e-> e.mailAddress }
                .toTypedArray()
                .run { assertThat(
                        this.toString()
                        , `is`(not(containsString("Honekawa11@mail.com"))))
                }
    }


    @Test
    fun getByFirstNameのテスト() {
        val empStream1 = employeeRepository.getByFirstName(firstNameSuneo)
        val empStream2 = employeeRepository.getByFirstName(firstNameSuneo)
        assertThat(empStream1.count().toInt(), `is`(11))
        assertThat(empStream2.count().toInt(), `is`(not(10)))
    }

    @Test
    fun streamByFirstNameのテスト() {
        val empStream1 = employeeRepository.streamByFirstName(firstNameSuneo)
        val empStream2 = employeeRepository.streamByFirstName(firstNameSuneo)
        assertThat(empStream1.count().toInt(), `is`(11))
        assertThat(empStream2.count().toInt(), `is`(not(10)))
    }

//    できない
//    @Test
//    fun queryByFirstNameのテスト() {
//        val empFuture1 = employeeRepository.queryByFirstName(firstNameSuneo)
//        println("aaaaaaa")
//        println("bbbbbbb")
//        val empFuture2 = employeeRepository.queryByFirstName(firstNameSuneo)
//        println("aaaaaaa")
//        println("bbbbbbb")
//
//        val empList1 = requireNotNull(empFuture1.join())
//        val empList2 = requireNotNull(empFuture2.join())
//        println("aaaaaaa")
//
//        assertThat(empList1.size, `is`(11))
//        assertThat(empList2.size, `is`(not(10)))
//    }

    @Test
    fun getFirstByFirstNameのテスト() {
        val empInterface = employeeRepository.getFirstByFirstName("Shizuka")
        assertThat(empInterface.getFirst(), `is`("Shizuka"))
    }

    @Test
    fun readByFirstName() {
    }

    @Test
    fun readByFirst5ByLastNameのテスト() {
    }

    @Test
    fun countByFirstNameのテスト() {
        val empInt = employeeRepository.countByFirstName(firstNameSuneo)
        assertThat(empInt, `is`(11))
    }

    @Test
    fun existsByFirstNameのテスト() {
        val empTrue = employeeRepository.existsByFirstName(firstNameSuneo)
        assertThat(empTrue, `is`(true))
    }

    @Test
    fun deleteByFirstNameのテスト() {
        val empDeleteList = employeeRepository.deleteByFirstName(firstNameSuneo)
        assertThat(empDeleteList.size, `is`(11))
        assertThat(empDeleteList.size, `is`(not(13)))

        empDeleteList.forEach { e ->
            assertThat(e.firstName, `is`(firstNameSuneo))
        }

    }

    @Test
    fun removeByFirstNameのテスト() {
        val empRemoveInt = employeeRepository.removeByFirstName(firstNameSuneo)
        assertThat(empRemoveInt, `is`(11))

        val empTrue = employeeRepository.existsByFirstName(firstNameSuneo)
        assertThat(empTrue, `is`(false))
    }

    @Test
    fun deleteFirst10ByFirstName(){
        val empdeleteInt10 = employeeRepository.deleteFirst10ByFirstName(firstNameSuneo)
        assertThat(empdeleteInt10, `is`(11))
    }

    @Test
    fun deleteFirstNameのテスト() {
        val empdeleteInt = employeeRepository.deleteFirstName(firstNameSuneo)
        assertThat(empdeleteInt, `is`(11))

        val empTrue = employeeRepository.existsByFirstName(firstNameSuneo)
        assertThat(empTrue, `is`(false))
    }

    @Test
    fun deleteFirstNameNativeのテスト() {
        val empDeleteInt = employeeRepository.deleteFirstNameNative(firstNameSuneo)
        assertThat(empDeleteInt, `is`(11))

        val empTrue = employeeRepository.existsByFirstName(firstNameSuneo)
        assertThat(empTrue, `is`(false))
    }

    @Test
    fun updateFirstNameのテスト() {
        val changeFirstName = "test"

        changeFirstName.run {
            val empDeleteInt = employeeRepository.updateFirstName(this, firstNameSuneo)
            assertThat(empDeleteInt, `is`(11))

            val empList = employeeRepository.readByFirstName(this)
            empList.forEach { e -> assertThat(e.firstName, `is`(this)) }
        }
    }
}