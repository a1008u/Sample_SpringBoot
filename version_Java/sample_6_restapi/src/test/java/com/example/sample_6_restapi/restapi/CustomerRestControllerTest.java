package com.example.sample_6_restapi.restapi;

import com.example.sample_6_restapi.domain.Customer;
import com.example.sample_6_restapi.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * スタブとモックの最大の違いは、
 * スタブは「受信メッセージのテスト」のために使うためのもので、
 * モックは「送信メッセージのテスト」のために使うものであるという点です。
 *
 * RestControllerのJunitはMockを行う
 * RestControllerのスタブはPostManを利用して行う
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest{
    @Autowired
    MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    /**
     * Mock
     * @throws Exception
     */
    @Test
    public void getCustomers_list_動作確認() throws Exception {

        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1,"t1","t2"));
        when(customerService.findAll()).thenReturn(customerList);

        mvc.perform(get("/api/customers/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"firstName\":\"t1\",\"lastName\":\"t2\"}]")));
    }

    /**
     * mock
     * @throws Exception
     */
//    @Test
//    public void getCustomers_page_動作確認() throws Exception {
//
//        String result1= mvc.perform(get("/api/customers/page"))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//
//        String result2 = mvc.perform(get("/api/customers/page"))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//        assertEquals(result1.length(), result2.length());
//    }

    /**
     * mock
     * @throws Exception
     */
    @Test
    public void getCustome_動作確認() throws Exception {
        String id = "1";
        Customer customer = new Customer(Integer.parseInt(id),"t1","t2");
        when(customerService.findOne(Integer.parseInt(id))).thenReturn(customer);

        mvc.perform(get("/api/customers/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("{\"id\":1,\"firstName\":\"t1\",\"lastName\":\"t2\"}")));
    }

    @Autowired
    ObjectMapper mapper;
    @Test
    public void postCustomers_動作確認() throws Exception {

        String id = "1";
        Customer customer = new Customer(Integer.parseInt(id),"t1","t2");
        when(customerService.create(customer)).thenReturn(customer);

        mvc.perform(post("/api/customers")
                .content(mapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"id\":1,\"firstName\":\"t1\",\"lastName\":\"t2\"}")));
    }

    @Test
    public void putCustomer() throws Exception {

        String id = "1";
        Customer customer = new Customer(Integer.parseInt(id),"t1","t2");
        when(customerService.update(customer)).thenReturn(customer);

        mvc.perform(put("/api/customers/update/{id}","1")
                    .content(mapper.writeValueAsString(customer))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"id\":1,\"firstName\":\"t1\",\"lastName\":\"t2\"}")));
    }

    @Test
    public void deleteCustomer() throws Exception {
        String firstName = "test1";
        String lastName = "test1";

        mvc.perform(delete("/api/customers/delete/{id}","1")
                .content(mapper.writeValueAsString(new Customer(1,firstName,lastName)))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")))
                .andExpect(content().string(not(containsString("12"))));
    }

}