package com.example.sample_spring_data_jpa.repository

import com.example.sample_spring_data_jpa.domain.Employee
import com.example.sample_spring_data_jpa.domain.Sex
import com.example.sample_spring_data_jpa.model.EmployeeInterface
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.query.Param
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.sql.Date
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.stream.Stream
import javax.persistence.QueryHint


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
    //@Query("SELECT e FROM Employee e WHERE LENGTH(e.firstName) = ?1")
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

    // 1件取得
    fun readFirstByFirstName(firstName:String):Employee

    fun readTop1ByFirstName(firstName:String):Employee

    // 10件取得
    fun readFirst10ByFirstName(firstName:String):List<Employee>

    fun readTop10ByFirstName(firstName:String):List<Employee>

    // 重複をなくす
    // fun readDistinctByFirstName(firstName:String):Employee

    // 大量データの検索(streamを用いて取得値を返す)
    fun getByFirstName(firstName: String):Stream<Employee>

    // フェッチサイズを調整することで、データ取得の処理時間を最適化する
    @QueryHints(QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "1000"))
    fun streamByFirstName(firstName: String):Stream<Employee>

    // 非同期 Future<List<Employee>> CompletableFuture<List<Employee>> ListenableFutureList<Employee>>
    //    @Async
    //    fun queryByFirstName(firstName: String):Future<List<Employee>>

    @Async
    fun queryByFirstName(firstName: String):CompletableFuture<List<Employee>>

    // 再モデリング
    fun getFirstByFirstName(firstName: String): EmployeeInterface


    // Page --------------------------------------------------------------

    //fun readByFirstName(firstName:String, pageble: Pageable): Page<Employee>

    // fun readByFirstName(firstName:String, pageble: Pageable): Slice<Employee>

    // Pageの情報が必要でないときは、Listでも返せる
    // fun readByFirstName(firstName:String, pageble: Pageable): List<Employee>

    //fun readByFirst5ByLastName(firstName:String, pageble: Pageable): Page<Employee>

    // 件数取得
    fun countByFirstName(firstName: String):Int

    // 存在確認
    fun existsByFirstName(firstName: String):Boolean

    // 削除 deleteBy removeBy deleteは該当対象分削除される
    // Listで削除対象のレコードを取得できる
    fun deleteByFirstName(firstName: String):List<Employee>

    // 整数型削除対象のレコード数が取得できる
    fun removeByFirstName(firstName: String):Int

    fun deleteFirst10ByFirstName(firstName: String):Int

    // JQPL --------------------------------------------------------------
    // CRUD全てを@Queryを利用することで記載することができる。(クエリメソッドだとupdateとdeleteができない)
    // clearAutomatically = trueでJPQL実行後の永続化コンテキストがクリアされる
    // @Param("パラメータ名")を設定することで、名前付きパラメーターとしてマッピングできる「:パラメーター名」で利用する
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM Employee e WHERE e.firstName = :firstName")
    fun deleteFirstName(@Param("firstName") firstName: String):Int

    // nativeQuery = trueとすることで通常のクエリをかける
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Employee  WHERE first_name = :firstName",nativeQuery = true)
    fun deleteFirstNameNative(@Param("firstName") firstName: String):Int

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Employee SET first_name = :targetFirstName WHERE first_name = :firstName",nativeQuery = true)
    fun updateFirstName(@Param("targetFirstName") targetFirstName: String
                        , @Param("firstName") firstName: String):Int


}



































