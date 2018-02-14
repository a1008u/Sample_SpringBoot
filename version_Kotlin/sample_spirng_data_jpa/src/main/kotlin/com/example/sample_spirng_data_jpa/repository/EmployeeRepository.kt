package com.example.sample_spirng_data_jpa.repository

import com.example.sample_spirng_data_jpa.domain.Employee
import com.example.sample_spirng_data_jpa.domain.Sex
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.sql.Date


/**
 * JpaRepository
 *  PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>インターフェースを継承したインターフェース
 */
@Repository
interface EmployeeRepository: JpaRepository<Employee,Int> {

    /**
     * findBy,readBy,getBy,queryBy,streamByなどの後にフィールド名を設定する
     * readByFirstName : null考慮
     * readByFirstNameIs : 一致比較のため、null考慮
     * readByFirstNameNot : 不一致条件で比較のため、null考慮されない
     *
     */
    fun readByFirstName(firstName:String):List<Employee>

    /**
     * readByFirstNameIgnoringCase : 大文字と小文字の区別なし + 条件一致
     * readByFirstNameNotIgnoringCase : 大文字と小文字の区別なし + 条件不一致
     */
    fun readByFirstNameIgnoringCase(firstName: String):List<Employee>

    fun readByFirstNameAndSex(firstName:String, sex: Sex):List<Employee>

    fun readByBirthdayGreaterThan(birthday:Date):List<Employee>

    fun readByFirstNameOrderByBirthday(firstName:String):List<Employee>

    fun readByFirstNameOrderByBirthdayDesc(firstName:String):List<Employee>

    fun readByFirstNameOrderByBirthdayDescSex(firstName:String):List<Employee>

    // 1県取得
    fun readFirstByFirstName(firstName:String):Employee

    fun readTop1ByFirstName(firstName:String):Employee

    // 10件取得
    fun readFirst10ByFirstName(firstName:String):List<Employee>

    fun readTop10ByFirstName(firstName:String):List<Employee>

    // 重複をなくす
    fun readDistinctByFirstName(firstName:String):Employee



}