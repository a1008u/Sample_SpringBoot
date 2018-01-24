
package com.example.sample_9_security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;

    /* @ManyToOne(fetch = FetchType.LAZY)
     * UserとCustomerを多対1の関係にするために用いる
     *
     * @JoinColumn(nullable = true, name = "username")
     * 外部キーのカラム名を指定する
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "username")
    private User user;
}