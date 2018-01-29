package com.example.sample_6_restapi.repository;

import com.example.sample_6_restapi.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
//@Sql(statements = {"INSERT INTO customers(first_name, last_name) VALUES('eeeee', 'aaaaa');"})
@Sql
//classpath:test/resources/com/example/sample_4_jpa/repository/CustomerRepositoryTest.sqlを実行する(test/resources/は表示されない)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * List取得での正常確認
     *
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
        listCustomer.forEach(B(0, (customer, i)-> {

            if(0==i) {
                // assertThat(customer.getId(), is(2));
                assertThat(customer.getFirstName(), is("eeeee"));
                assertThat(customer.getLastName(), is("aaaaa"));
            }

            if(1==i) {
                // assertThat(customer.getId(), is(1));
                assertThat(customer.getFirstName(), is("eeeee"));
                assertThat(customer.getLastName(), is("ddddd"));
            }

        }));

    }

    /**
     * List取得での異常確認
     */
    @Test
    public void findAllOrderByName_異常(){

        // SetUp(Before)
        List<Customer> listCustomer = customerRepository.findAllOrderByName();

        // Exercise & Verify
        listCustomer.forEach(B(0, (customer, i)-> {

            if(0==i) {
                assertThat(customer.getId(), is(not(1)));
                assertThat(customer.getFirstName(), is(notNullValue()));
                assertThat(customer.getLastName(),  is(notNullValue()));
            }

            if(1==i) {
                assertThat(customer.getId(), is(not(2)));
                assertThat(customer.getFirstName(), is(notNullValue()));
                assertThat(customer.getLastName(), is(notNullValue()));
            }

        }));

    }

    /**
     * Pageの正常起動確認
     */
    @Test
    public void findAllOrderByName_page_正常(){

        // SetUp(Before)
        Pageable pageable = new PageRequest(0, 3);
        Page<Customer> pageCustomer = customerRepository.findAllOrderByName(pageable);

        // Exercise & Verify
        assertThat(pageCustomer.getSize(), is(3)); // 1ページのデータ数
        assertThat(pageCustomer.getNumber(), is(0)); // 現在のページ
        assertThat(pageCustomer.getTotalPages(), is(1)); // 全ページ数
        assertThat(pageCustomer.getTotalElements(), is(2L)); // 全データ数

        pageCustomer.getContent()
                    .forEach(B(0, (customer, i) -> {
                                if(0==i) {
                                    assertThat(customer.getId(), is(2));
                                    assertThat(customer.getFirstName(), is("eeeee"));
                                    assertThat(customer.getLastName(), is("aaaaa"));
                                }

                                if(1==i) {
                                    assertThat(customer.getId(), is(1));
                                    assertThat(customer.getFirstName(), is("eeeee"));
                                    assertThat(customer.getLastName(), is("ddddd"));
                                }
                            }
                    ));

    }

    /**
     *　Pageの異常起動確認
     */
    @Test
    public void findAllOrderByName_page_異常(){

        // SetUp(Before)
        Pageable pageable = new PageRequest(0, 3);
        Page<Customer> pageCustomer = customerRepository.findAllOrderByName(pageable);

        // Exercise & Verify
        assertThat(pageCustomer.getSize(), is(not(2))); // 1ページのデータ数
        assertThat(pageCustomer.getNumber(), is(not(1))); // 現在のページ
        assertThat(pageCustomer.getTotalPages(), is(not(2))); // 全ページ数
        assertThat(pageCustomer.getTotalElements(), is(not(1L))); // 全データ数

        pageCustomer.getContent()
                .forEach(B(0, (customer, i) -> {
                        if(0==i) {
                            assertThat(customer.getId(), is(not(1)));
                            assertThat(customer.getFirstName(), is(notNullValue()));
                            assertThat(customer.getLastName(),  is(notNullValue()));
                        }

                        if(1==i) {
                            assertThat(customer.getId(), is(not(2)));
                            assertThat(customer.getFirstName(), is(notNullValue()));
                            assertThat(customer.getLastName(), is(notNullValue()));
                        }
                    }
                ));

    }

    public static <T> Consumer<T> B(int start, ObjIntConsumer<T> consumer) {
        int counter[] = { start };
        return obj -> consumer.accept(obj, counter[0]++);
    }

}






