package com.example.sample_9_security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
// 循環参照によって「StackOverflowエラー」がはっせいするため、toStringメソッドからcustomersフィールドの出力を除く
@ToString(exclude = "customers")
public class User {
    @Id
    private String username;

    // REST APIでUserクラスをJSON出力する場合に、パスワードフィールドを除外する
    @JsonIgnore
    private String encodedPassword;

    @JsonIgnore
    /* cascade = CascadeType.ALL
     * Userの永続化操作や削除操作を関連先のCuustomerにも伝達させることができる
     * Userを削除すると、Customerも削除される。
     *
     * fetch = FetchType.LAZY
     * 関連するエンティティを遅延ロードさせることができる
     * CustomersフィールドにアクセスしたタイミングでCustomerエンティティが取得される
     *
     * mappedBy = "user"
     * 双方の関連にする場合に関連先でのプロパティ名を指定する
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Customer> customers;
}