package com.example.sample_6_restapi_mysql.domain;

import javax.persistence.*;


/**
 * @Entity
 * JPAのエンティティであることを示す
 *
 * @Table(name = "customers")
 * エンティティに対応するテーブル名を指定
 * デフォルトでは「テーブル名=クラス名」  ＊(name="任意のテーブル名")にも指定できる
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id // 主キー
    @GeneratedValue // 主キーがDBで自動昨晩されることを示す
    private Integer id;

    @Column(nullable = false) // カラムを示す
    private String firstName;

    @Column(nullable = false) // カラムを示す
    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
