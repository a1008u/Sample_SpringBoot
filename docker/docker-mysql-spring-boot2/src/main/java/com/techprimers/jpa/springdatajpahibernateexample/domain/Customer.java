package com.techprimers.jpa.springdatajpahibernateexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id // 主キー
    @GeneratedValue // 主キーがDBで自動昨晩されることを示す
    private Integer id;

    @Column(nullable = false) // カラムを示す
    private String firstName;

    @Column(nullable = false) // カラムを示す
    private String lastName;
}
