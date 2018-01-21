package com.example.sample_4_jdbc.repository;

import com.example.sample_4_jdbc.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;


/*
 * @Transactionalアノテーション
 * 「クラス・レベル」に付いたクラスを「DIコンテナ」から取得すると、
 * 　そのクラスの各メソッドが他のクラスから呼ばれた場合に、自動的に「DBトランザクション」の制御が行なわれる。
 *
 * (a)「メソッドが正常終了した場合」は、「DBトランザクション」がコミット
 * (b)「実行時例外が発生した場合」は、「DBトランザクション」がロールバック
 *
 * 「Dlコンテナ」によって、各メソッドに前後処理が追加されたクラスが、動的に生成される
 */
@Repository
@Transactional
public class CustomerRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    SimpleJdbcInsert insert;

    /**
     * 初期化
     */
    @PostConstruct
    public void init() {
        insert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations()) //
                                                                 .withTableName("customers") // テーブル名
                                                                 .usingGeneratedKeyColumns("id"); // 主キー名
    }

    /**
     * 「NamedParameterJdbcTemplate」の「query」メソッドを用いて、SQLの実行結果を「Javaオブジェクト」の「リスト」として取得。
     * 「ResultSet」を「Javaオブジェクト」に変換するための「RowMapper」は、次のメソッドでも使うため、フィールドに持たせる。
     */
    private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        return new Customer(id, firstName, lastName);
    };

    /**
     *
     */
    public List<Customer> findAll() {
        List<Customer> customers = jdbcTemplate.query("SELECT id,first_name,last_name FROM customers ORDER BY id"
                                                        , customerRowMapper);
        return customers;
    }

    /**
     *
     */
    public Customer findOne(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject("SELECT id,first_name,last_name FROM customers WHERE id=:id"
                                            , param
                                            , customerRowMapper);
    }

    /**
     *　BeanPropertySqlParameterSourceを使うことで、
     *  Javaオブジェクトのフィールド名と値をマッピングしたSqlParameterSourceが自動的に作成
     */
    public Customer save(Customer customer) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(customer);

        if (customer.getId() == null) {
            // CRUDのC 生成後にidをリターンする(idは自動生成されたことを確かめるため)
            Number key = insert.executeAndReturnKey(param);
            customer.setId(key.intValue());
        } else {
            // CRUDのU
            jdbcTemplate.update("UPDATE customers SET first_name=:firstName, last_name=:lastName WHERE id=:id", param);
        }

        return customer;
    }


    /**
     * deleteメソッド
     */
    public void delete() {
        SqlParameterSource param = new MapSqlParameterSource();
        jdbcTemplate.update("DELETE FROM customers",param);
    }

    public void delete(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update("DELETE FROM customers WHERE id=:id", param);
    }
}
