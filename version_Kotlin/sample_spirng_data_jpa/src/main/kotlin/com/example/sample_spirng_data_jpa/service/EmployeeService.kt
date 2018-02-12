package com.example.sample_spirng_data_jpa.service

import com.example.sample_spirng_data_jpa.domain.Employee
import com.example.sample_spirng_data_jpa.repository.EmployeeRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class EmployeeService(private val employeeRepository: EmployeeRepository){

    // 検索 ------------------------------------------------------------------

    /**
     * getOneメソッド
     *  レコードを参照するEntityを返す。
     *  そのため、遅延実行（Entityを確認するタイミングで実行される）となる。
     */
    fun getOne(id:Int) = employeeRepository.getOne(id)

    /**
     * 問い合わせの　SQLが発行され、レコードの内容が設定されたエンティティーが返却される。
     */
    fun findOne(id:Int) = employeeRepository.findOne(id)


    /**
     * 全件取得
     */
    fun findAll():List<Employee> =employeeRepository.findAll()

    /**
     * 全件取得およびソートのハンドリング
     */
    fun findAllSortFirstName():List<Employee> = employeeRepository.findAll(Sort("firstName"))

    /**
     * 全件取得およびソートのハンドリング(より詳細に)
     */
    fun findAllSortFirstName2():List<Employee> = employeeRepository.findAll(Sort(Sort.Order(Sort.Direction.ASC
                                            ,"firstName"
                                            ,Sort.NullHandling.NULLS_FIRST)))

    /**
     * 全件取得（employeeインスタンスのフィールドに設定している値を元に取得する）
     * ExampleMatcher.matching() -前方一致　後方一致　部分一致　nullを含むなどを設定する-
     *      .withIgnoreNullValues() 値がnullのフィールドは取得条件から除外する
     */
    fun findAll(employee: Employee):List<Employee> = employeeRepository.findAll(Example.of(employee, ExampleMatcher.matching().withIgnoreNullValues()))

    /**
     * レコード数を取得
     */
    fun count() = employeeRepository.count()

    /**
     * レコード数を取得する（employeeインスタンスのフィールドに設定している値を元に取得する）
     */
    fun count(employee: Employee) = employeeRepository.count(Example.of(employee))


}