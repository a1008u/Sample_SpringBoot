package com.example.sample_4_jpa;

import com.example.sample_4_jpa.domain.Customer;
import com.example.sample_4_jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


/**
 * @RunWith(SpringRunner.class)
 *  テストを実行する制御クラスを指定する
 *
 *  @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
 *   ＊デフォルトはH2（設定で変更できる）
 *   ＊replace属性にReplace.NONEを指定することで、プロダクションと同じDBを利用できる
 *
 *  @DataJpaTest
 * 　JPAコンポーネントをテストするために指定
 *   ＊インメモリデータベースを使ったテストとなる
 *
 *   @Sql
 *    このクラスのテストメソッドではSQLスクリプトとして実行する
 *    同一のクラスパス上でクラスパス（クラス名.sql）またはクラスパス（クラス名メソッド名.sql）を実行する
 *    または、パスを指定するか直接sqlを記載するこ共できる
 */
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
//@Sql(statements = {"INSERT INTO customers(first_name, last_name) VALUES('eeeee', 'aaaaa');"})
@Sql
//classpath:test/resources/com/example/sample_4_jpa/repository/CustomerRepositoryTest.sqlを実行する(test/resources/は表示されない)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * @SqlGroup
     *   value属性に@Sqlアノテーションをしていることで、複数の@Sqlアノテーションをしようすることができる
     *
     * @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "")
     * 　基本はテスト実行のsqlはテスト前に実行するが、上記を指定することで実行後にsqlを実行する
     *
     */
    @Test
//    @SqlGroup({
//            @Sql
//            ,@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "")
//    })
    public void findAllOrderByName_正常(){

        // SetUp(Before)
        List<Customer> listCustomer = customerRepository.findAllOrderByName();

        // Exercise & Verify
        listCustomer.forEach(customer -> {

            if("eeeee"==customer.getFirstName()) assertThat(customer.getFirstName(), is("eeeee"));
            if("aaaaa"==customer.getFirstName()) assertThat(customer.getLastName(), is("aaaaa"));
            if("Nobita"==customer.getFirstName()) assertThat(customer.getFirstName(), is("Nobita"));
            if("Nobi"==customer.getFirstName()) assertThat(customer.getLastName(), is("Nobi"));


        });

    }

}






