package com.example.sample_spring_data_jpa_relationship.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import java.sql.Date
import javax.persistence.*
import javax.validation.constraints.Digits

/**
 * @OneToOne
 * このエンティティとフィールドのエンティティが一対一の関係であることを定義する
 *  mappedBy 双方向のリレーションシップを定義する場合に利用(所有者側に設定)＊name     統合列の列名を指定
 *
 * 所有者側　：Employeeクラス       *こちらにも設定をすると、双方向のリレーション
 * 被所有者側：Authenticationクラス *こちらだけに設定の場合は、一方向のリレーション
 *
 * -------------------------------------------------------------------
 * @ManyToOne
 * 一対多の関係を定義する
 *  optional
 *
 * @JoinColumn
 *  所有者側のエンティティに指定してリレーションシップを定義する
 *  name
 *  insertable :被所有者側(所有者側)のエンティティから所有者側(被所有者側)のエンティティが挿入されないようにする
 *  updatable  :被所有者側(所有者側)のエンティティから所有者側(被所有者側)のエンティティが更新されないようにする
 *
 * 所有者側　：Employeeクラス   *こちらだけに設定の場合は、一方向のリレーション
 * 被所有者側：Departmentクラス　*こちらにも設定をすると、双方向のリレーション
 *
 * -------------------------------------------------------------------
 * @ManyToMany
 * エンティティ自身と、@ManyToManyアノテーションをつけたフィールドが多対多の関係であることを定義する
 * 統合列の定義は、所有者、被所有者側のエンティティに定義せず、それぞれの主キーフィールドを持つ統合テーブルが自動的に生成される
 *
 * @JoinTable
 * 統合テーブルを自分で作成できる
 * 　name  ：統合テーブルのテーブル名
 *  joinColumns：@JoinColumnアノテーションのname属性に、このエンティティと統合するための統合テーブル列名を指定
 *  inverseJoinColumns : @JoinColumnアノテーションのname属性に、統合先エンティティと統合するための統合テーブルの列名を指定
 *
 * 所有者側　：Employeeクラス   *こちらだけに設定の場合は、一方向のリレーション
 * 被所有者側：Authorizationクラス　*こちらにも設定をすると、双方向のリレーション
 *
 *
 */


@Entity
@Table(name = "employee", uniqueConstraints = [(UniqueConstraint(columnNames = ["tel"]))], indexes= [(Index(columnList = "tel", name = "indexTel"))])
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var no: Int = 0,

        @Column(unique = false)
        val firstName:String = "unregister",

        @Column
        val lastName:String = "unregister",

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val sex: Sex = Sex.unregister,

        @Column(length = 100)
        val tel: String = "unregister",

        @Column
        val birthday: Date =  Date.valueOf("0001-01-01"),

        @Column
        var mailAddress:String = "unregister",

        @Column(precision = 10, scale = 2)
        val salary: BigDecimal = 000.000.toBigDecimal(),

        //,@OneToOne(mappedBy = "employee") val authentication: Authentication
        @ManyToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)//(optional = false)
        @JoinColumn(name = "department_code")
//        @JsonManagedReference // 双方向のDepartmentを表示させる
        val department: Department = Department()
//                    ,@ManyToMany val authorizationList: List<Authorization>
//                    ,@ManyToMany // 主キーを使った方法
//                    　@JoinTable(name = "employeeAuthorization"
//                    　,joinColumns = [(JoinColumn(name = "employeeNo"))]
//                    　,inverseJoinColumns = [(JoinColumn(name = "authorizationRole"))])val authorizationList: List<Authorization>
//                    ,@ManyToMany // 主キーを使用しない方法
//                     @JoinTable(name = "employeeAuthorization"
//                      ,joinColumns = [(JoinColumn(name = "employeeFirstName", referencedColumnName = "firstName"))]
//                      ,inverseJoinColumns = [(JoinColumn(name = "authorizationName", referencedColumnName = "name"))])
//                     val authorizationList: List<Authorization>
){
        override fun toString(): String{
                return "{student: ${this.birthday}}}"
        }
}


enum class Sex {
    unregister, male, female
}


data class EmployeeDto(
        var no:Int = 0,
        var firstName:String = "test"
        , var lastName:String = "test"
        , @Enumerated(EnumType.STRING) var sex: Sex = Sex.male
        , @Length(max = 100) var tel: String = "998887777"
        , var birthday: Date =  Date.valueOf("0001-01-01")
        , var mailAddress:String = "test@test"
        , @Digits(integer = 10, fraction = 2) var salary: BigDecimal = 999.99.toBigDecimal()
        , var department: TmpDepartment = TmpDepartment()
){
        companion object {
                fun fromDto(dto: EmployeeDto) : Employee {
                        return Employee(Int.MAX_VALUE
                                , dto.firstName
                                , dto.lastName
                                , dto.sex
                                , dto.tel
                                , dto.birthday
                                , dto.mailAddress
                                , dto.salary
                                , Department(dto.department.code, dto.department.name))
                }

                fun toDto(employee: Employee) = EmployeeDto(employee.no
                        , employee.firstName
                        , employee.lastName
                        , employee.sex
                        , employee.tel
                        , employee.birthday
                        , employee.mailAddress
                        , employee.salary
                        , TmpDepartment(employee.department.code, employee.department.name))

        }
}
class TmpEmployee(var no:Int = 0,
                  var firstName:String = "test"
                  ,var lastName:String = "test"
                  ,@Enumerated(EnumType.STRING) var sex: Sex = Sex.male
                  ,@Length(max = 100) var tel: String = "998887777"
                  ,var birthday: Date =  Date.valueOf("0001-01-01")
                  ,var mailAddress:String = "test@test"
                  ,@Digits(integer = 10, fraction = 2) var salary: BigDecimal = 999.99.toBigDecimal())

