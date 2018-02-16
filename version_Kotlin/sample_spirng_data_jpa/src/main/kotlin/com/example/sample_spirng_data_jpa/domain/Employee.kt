package com.example.sample_spirng_data_jpa.domain

import java.math.BigDecimal
import java.sql.Date
import javax.persistence.*

/**
 * @Idへのアクセス方法設定　。。。。基本はFIELDでOK
 * @Access(AccessType.FIELD) JPAはフィールドのメンバ変数を直接参照かつ更新
 * @Access(AccessType.PROPERTY)　JPAはフィールドのアクセサメソッドを介して、直接参照かつ更新
 *
 * @Id 非null制約
 * エンティティーのフィールドに@Idアノテーションを指定することで、主キーとして扱える
 *
 * @Table
 *  name
 *  schema
 *  uniqueConstraints
 *
 * @Column
 *  nullable
 *  unique
 *  length
 *  precision
 *  scale
 *  columnDefinition
 *
 * @GeneratedValue
 *  レコードを追加するときに、その主キーフィールドは自動生成され一意な値が設定
 *  主キー以外は無視される
 *
 *  strategy = GenerationType.IDENTITY
 *      hibernate_sequencesテーブルを作成し、一意の値を生成する
 *
 */

@Entity
@Table(name = "employee", schema = "test2", uniqueConstraints = [(UniqueConstraint(columnNames = ["tel"]))], indexes= [(Index(columnList = "tel", name = "indexTel"))])
data class Employee(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int = -1
                    ,@Column(unique = false) val firstName:String = "test"
                    ,@Column val lastName:String = "test"
                    ,@Column(nullable = false) @Enumerated(EnumType.STRING) val sex: Sex = Sex.male
                    ,@Column(length = 100) val tel: String = "998887777"
                    ,@Column val birthday:Date =  Date.valueOf("0001-01-01")
                    ,@Column var mailAddress:String = "test@test"
                    ,@Column(precision = 10, scale = 2) val salary:BigDecimal = 999.99.toBigDecimal())


enum class Sex {
    male, female
}