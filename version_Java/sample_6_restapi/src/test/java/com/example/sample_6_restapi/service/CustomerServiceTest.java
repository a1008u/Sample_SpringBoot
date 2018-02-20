package com.example.sample_6_restapi.service;


import com.example.sample_6_restapi.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@Sql
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @After
    public void doBefore() {
        // 初期化
        customerService.deleteAll();
    }

    @Test
    public void findAll_正常(){

        // SetUp(Before)
        List<Customer> listCustomer = customerService.findAll();

        // Exercise & Verify
        assertThat(listCustomer.size(), is(2));

        listCustomer.forEach(B(0, (customer, i)-> {

            assertThat(customer.getFirstName(), is("service"));

            String target = (0==i)? "service1" :"service4";
            assertThat(customer.getLastName(), is(target));

        }));
    }

    @Test
    public void findAll_異常(){
        // SetUp(Before)
        List<Customer> listCustomer = customerService.findAll();

        // Exercise & Verify
        assertThat(listCustomer.size(), is(2));
        listCustomer.forEach(B(0, (customer, i)-> {

            if(0==i) {
                assertThat(customer.getId(), is(not(6)));
                assertThat(customer.getFirstName(), is(notNullValue()));
                assertThat(customer.getLastName(),  is(notNullValue()));
            }

            if(1==i) {
                assertThat(customer.getId(), is(not(5)));
                assertThat(customer.getFirstName(),  is(notNullValue()));
                assertThat(customer.getLastName(),  is(notNullValue()));
            }

        }));
    }

    @Test
    public void findAll_Page_正常_異常(){

        // SetUp(Before)
        Customer c = new Customer();
        c.setFirstName("sTest2");
        c.setLastName("sTest1");

        customerService.create(c);
        Integer id = c.getId();
        String FirstName = c.getFirstName();
        String LastName = c.getLastName();


        Pageable pageable = new PageRequest(0, 6);
        Page<Customer> pageCustomer = customerService.findAll(pageable);

        // Exercise & Verify
        assertThat(pageCustomer.getSize(), is(6)); // 1ページのデータ数
        assertThat(pageCustomer.getNumber(), is(0)); // 現在のページ
        assertThat(pageCustomer.getTotalPages(), is(1)); // 全ページ数
        assertThat(pageCustomer.getTotalElements(), is(3L)); // 全データ数

        pageCustomer.getContent()
                .forEach(B(0, (customer, i) -> {
                            if(0==i) {
                                assertThat(customer.getId(), is(id));
                                assertThat(customer.getFirstName(), is(FirstName));
                                assertThat(customer.getLastName(), is(LastName));
                            }

                            if(1==i) {
                                assertThat(customer.getId(), is(not(3)));
                                assertThat(customer.getFirstName(), is(notNullValue()));
                                assertThat(customer.getLastName(), is(notNullValue()));
                            }

                            if(2==i) {
                                assertThat(customer.getId(), is(1));
                                assertThat(customer.getFirstName(), is("service"));
                                assertThat(customer.getLastName(), is("service4"));
                            }
                        }
                ));
    }


    @Test
    public void create_findOne_update_delete_正常(){

        // SetUp(Before)
        Customer customer = new Customer();
        customer.setFirstName("sTest2");
        customer.setLastName("sTest1");

        customerService.create(customer);
        Integer id = customer.getId();

        // findOneのテスト
        Customer c = customerService.findOne(id);
        assertThat(c.getId(), is(id));
        assertThat(c.getFirstName(), is("sTest2"));
        assertThat(c.getLastName(), is("sTest1"));

        // updateのテスト
        c.setFirstName("renameTest2");
        c.setLastName("renameTest1");
        customerService.update(c);

        Customer reC = customerService.findOne(id);
        assertThat(reC.getId(), is(id));
        assertThat(reC.getFirstName(), is("renameTest2"));
        assertThat(reC.getLastName(), is("renameTest1"));

        // deleteのテスト
        customerService.delete(id);
        Customer deC = customerService.findOne(id);
        assertThat(deC, is(nullValue()));

    }

    @Test
    public void create_findOne_update_delete_異常() {

        // SetUp(Before)
        Customer customer = new Customer();
        customer.setFirstName("out1");
        customer.setLastName("out2");

        customerService.create(customer);
        Integer id = customer.getId();

        // findOneのテスト
        Customer c = customerService.findOne(id);
        assertThat(c.getId(), is(not(5)));
        assertThat(c.getFirstName(), is(not("outX1")));
        assertThat(c.getLastName(), is(not("outX2")));

        // updateのテスト
        c.setFirstName("outX1");
        c.setLastName("outX2");
        customerService.update(c);

        Customer reC = customerService.findOne(id);
        assertThat(c.getId(), is(not(9)));
        assertThat(c.getFirstName(), is(not("sTest2")));
        assertThat(c.getLastName(), is(not("sTest1")));

    }

    public static <T> Consumer<T> B(int start, ObjIntConsumer<T> consumer) {
        int counter[] = { start };
        return obj -> consumer.accept(obj, counter[0]++);
    }
}
