package com.example.sample_spirng_data_jpa.service

import com.example.sample_spirng_data_jpa.domain.Employee
import com.example.sample_spirng_data_jpa.model.EmployeeForm
import com.example.sample_spirng_data_jpa.repository.EmployeeRepository
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors


/**
 * リポジトリーインターフェースに用意されているメソッドの特徴
 *  - 主キーでの削除
 *  - 主キーでの検索
 *  - domainの追加（マッピング）
 *
 *  できないこと
 *  - 主キー以外のフィールドを条件としたレコードの削除
 *  - 主キー以外のフィールドで、文字列以外の項目の条件を指定したレコードの取得、レコード件数の取得
 *  - 以上、以下のような条件を指定したレコードの取得、レコード件数の取得
 *
 *
 */

@Service
@Transactional
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


    // フィールドを指定した検索メソッド(主キー以外の検索方法1)
    fun fjindAllExample(employeeForm: EmployeeForm):List<Employee>{
       return Employee().run {
           BeanUtils.copyProperties(employeeForm, this)
           employeeRepository.findAll(Example.of(this))
       }
    }

    // フィールドを指定した検索メソッド(主キー以外の検索方法2_repositoryにクエリーメソッドを定義する)
    fun readByFirstName(employeeForm: EmployeeForm): List<Employee> = employeeRepository.readByFirstName(employeeForm.firstName)

    fun readByFirstNameAndSex(employeeForm: EmployeeForm): List<Employee>
            = employeeRepository.readByFirstNameAndSex(employeeForm.firstName, employeeForm.sex)

    fun readByBirthdayGreaterThan(employeeForm: EmployeeForm): List<Employee>
            = employeeRepository.readByBirthdayGreaterThan(employeeForm.birthday)


    // 更新 ------------------------------------------------------------------
    /**
     * DBへの書き込みのタイミング
     *  - テーブルにアクセスする前
     *  - リポジトリーのflushメソッド実行時
     *  - トランザクション終了時
     */

    fun updateAllMailaddress() {

        val employeeAll = findAll()
        findAll().forEach{e ->print("初回です  $e")}
        employeeAll.forEach { employee -> employee.mailAddress = "change@change" }
        findAll().forEach{e ->print("updateしました  $e")}
        employeeAll.forEach { employee -> employee.mailAddress = "change@change" }
        findAll().forEach{e ->print("updateしていない(同じ値なので)  $e")}
        employeeAll.forEach { employee -> employee.mailAddress = "update@update" }
        employeeRepository.flush()
        findAll().forEach{e ->print("updateしました  $e")}

        // トランザクション管理を@Transactionalで行なっているのでupdateされる
        employeeAll.forEach { employee -> employee.mailAddress = "tttttt@aaaaaa" }
    }

    // 挿入 ------------------------------------------------------------------
    /**
     * saveメソッドを使うことで、DBに新規データを挿入できる
     * また、複数のインスタンスもList形式で渡すことで利用できる
     * saveメソッドのタイミング
     *  - テーブルにアクセスする前
     *  - リポジトリーのflushメソッド実行時　即時
     *  - トランザクション終了時
     */
    fun saveEmployee(employeeForm: EmployeeForm) {

        Employee().apply {
            BeanUtils.copyProperties(employeeForm, this)
            employeeRepository.save(this)
        }
    }

    // 削除 ------------------------------------------------------------------
    /**
     * deleteメソッドのタイミング
     *  - テーブルにアクセスする前
     *  - リポジトリーのflushメソッド実行時　即時
     *  - トランザクション終了時
     */
    fun deleteById(Id: Int) = employeeRepository.delete(Id)

    fun deleteByEmployee(employeeForm: EmployeeForm) {

        Employee().apply {
            BeanUtils.copyProperties(employeeForm, this)
            employeeRepository.delete(this)
        }
    }

    /**
     * バルク削除(delete文を発行する回数を1会だけにする)　--------------------------
     * バルク削除される可能性があるレコードを更新する場合、悲観ロックを使用して、
     * 別のトランザクションからレコードが削除されないようにはいたロックをかける。
     *
     */
    fun deleteAll() = employeeRepository.deleteAllInBatch()


    // 複数削除
    fun deleteEmployeeList(EmployeeFromList: List<EmployeeForm>) {

        val employeeList = EmployeeFromList
                .stream()
                .map { e ->  Employee().apply { BeanUtils.copyProperties(e, this) } }
                .collect(Collectors.toList())

        employeeRepository.deleteInBatch(employeeList)
    }

}






























