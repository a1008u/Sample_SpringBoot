package com.example.sample_spring_data_jpa.service

import com.example.sample_spring_data_jpa.domain.Employee
import com.example.sample_spring_data_jpa.model.EmployeeDto
import com.example.sample_spring_data_jpa.model.EmployeeInterface
import com.example.sample_spring_data_jpa.repository.EmployeeRepository
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.concurrent.Future
import java.util.stream.Collectors
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


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
     * getOneメソッド　 ＊findOneでも可能
     *  レコードを参照するEntityを返す。
     *  そのため、遅延実行（Entityを確認するタイミングで実行される）となる。
     */
    fun getOne(id:Int) = EmployeeDto.toDto(requireNotNull(employeeRepository.getOne(id)))

    /**
     * 全件取得
     */
    fun findAll():List<EmployeeDto> = employeeRepository
            .findAll()
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    /**
     * 全件取得およびソートのハンドリング
     */
    fun findAllSortFirstName():List<EmployeeDto> = employeeRepository
            .findAll(Sort("firstName"))
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    /**
     * 全件取得およびソートのハンドリング(より詳細に)
     */
    fun findAllSortFirstName2():List<EmployeeDto> = employeeRepository
            .findAll(Sort(Sort.Order(Sort.Direction.ASC,"firstName",Sort.NullHandling.NULLS_FIRST)))
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    /**
     * 全件取得（employeeインスタンスのフィールドに設定している値を元に取得する）
     * ExampleMatcher.matching() -前方一致　後方一致　部分一致　nullを含むなどを設定する-
     *      .withIgnoreNullValues() 値がnullのフィールドは取得条件から除外する
     */
    fun findAll(employeeDto: EmployeeDto):List<EmployeeDto> = employeeRepository
            .findAll(Example.of(EmployeeDto.fromDto(employeeDto), ExampleMatcher.matching().withIgnoreNullValues()))
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    /**
     * レコード数を取得
     */
    fun count() = employeeRepository.count().toInt()

    /**
     * レコード数を取得する（employeeインスタンスのフィールドに設定している値を元に取得する）
     */
    fun count(employee: Employee) = employeeRepository.count(Example.of(employee))


    // フィールドを指定した検索メソッド(主キー以外の検索方法1)
    fun findAllExample(employeeDto: EmployeeDto):List<Employee>{
       return Employee().run {
           BeanUtils.copyProperties(employeeDto, this)
           employeeRepository.findAll(Example.of(this))
       }
    }

    // フィールドを指定した検索メソッド(主キー以外の検索方法2_repositoryにクエリーメソッドを定義する)
    fun readByFirstName(employeeDto: EmployeeDto): List<EmployeeDto> = employeeRepository
            .readByFirstName(employeeDto.firstName)
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    fun readByFirstNameAndSex(employeeDto: EmployeeDto): List<EmployeeDto> = employeeRepository
            .readByFirstNameAndSex(employeeDto.firstName, employeeDto.sex)
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    fun readByBirthdayGreaterThan(employeeDto: EmployeeDto): List<EmployeeDto> = employeeRepository
            .readByBirthdayGreaterThan(employeeDto.birthday)
            .map{ e -> EmployeeDto.toDto(requireNotNull(e)) }
            .toList()

    /**
     * 永続化コンテキストから切り離すことで、エンティティーオブジェクトが永続化コンテキスト内に残り続けることを避けパフォーマンスアップ
     */
    @PersistenceContext
    lateinit var entityManager: EntityManager
    fun getByFirstName(empemployeeDto: EmployeeDto):List<EmployeeDto>{

        val employeeStream = employeeRepository.getByFirstName(empemployeeDto.firstName)

        return employeeStream
                .map { e -> entityManager.detach(e) }
                .collect(Collectors.toList()) as List<EmployeeDto>? ?: Arrays.asList(EmployeeDto())
    }

    /**
     * 非同期処理（あまり意味がない処理になっている。。。）
     */
    fun queryByFirstName(employeeDto: EmployeeDto):List<EmployeeDto>{

        val employeeListFuture :Future<List<Employee>> = employeeRepository.queryByFirstName(employeeDto.firstName)
        return employeeListFuture
                .get()
                .stream()
                .map { e -> entityManager.detach(e) }
                .collect(Collectors.toList()) as List<EmployeeDto>? ?: Arrays.asList(EmployeeDto())
    }

    /**
     * 再モデリング
     */
    fun getFirstByFirstName1(employeeDto: EmployeeDto): EmployeeInterface {
        return employeeRepository
                .getFirstByFirstName(employeeDto.firstName)
    }


    // 更新 ------------------------------------------------------------------
    /**
     * DBへの書き込みのタイミング
     *  - テーブルにアクセスする前
     *  - リポジトリーのflushメソッド実行時
     *  - トランザクション終了時
     */

    fun updateFirstName(targetFirstName:String, employeeDtoList: List<EmployeeDto>) : Int{
        return employeeDtoList
                .map { e -> employeeRepository.updateFirstName(targetFirstName, e.firstName)}
                .map { employeeRepository.flush()}
                .count()
    }

//    fun updateAllMailaddress() {
//
//        val employeeAll = findAll()
//        findAll().forEach{e ->print("初回です  $e")}
//        employeeAll.forEach { employee -> employee.mailAddress = "change@change" }
//        findAll().forEach{e ->print("updateしました  $e")}
//        employeeAll.forEach { employee -> employee.mailAddress = "change@change" }
//        findAll().forEach{e ->print("updateしていない(同じ値なので)  $e")}
//        employeeAll.forEach { employee -> employee.mailAddress = "update@update" }
//        employeeRepository.flush()
//        findAll().forEach{e ->print("updateしました  $e")}
//
//        // トランザクション管理を@Transactionalで行なっているのでupdateされる
//        employeeAll.forEach { employee -> employee.mailAddress = "tttttt@aaaaaa" }
//    }

    // 挿入 ------------------------------------------------------------------
    /**
     * saveメソッドを使うことで、DBに新規データを挿入できる
     * また、複数のインスタンスもList形式で渡すことで利用できる
     * saveメソッドのタイミング
     *  - テーブルにアクセスする前
     *  - リポジトリーのflushメソッド実行時　即時
     *  - トランザクション終了時
     */
    fun saveEmployee(employeeDto: EmployeeDto) =
            EmployeeDto.toDto(requireNotNull(employeeRepository.save(EmployeeDto.fromDto(employeeDto))))


    // 削除 ------------------------------------------------------------------
    /**
     * deleteメソッドのタイミング
     *  - テーブルにアクセスする前
     *  - リポジトリーのflushメソッド実行時　即時
     *  - トランザクション終了時
     */
    fun deleteById(Id: Int) = employeeRepository.delete(Id)

    fun deleteByEmployee(employeeDto: EmployeeDto) {

        Employee().apply {
            BeanUtils.copyProperties(employeeDto, this)
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
    fun deleteEmployeeList(employeeFromList: List<EmployeeDto>) {

        val employeeList = employeeFromList
                .stream()
                .map { e ->  Employee().apply { BeanUtils.copyProperties(e, this) } }
                .collect(Collectors.toList())

        employeeRepository.deleteInBatch(employeeList)
    }

}






























