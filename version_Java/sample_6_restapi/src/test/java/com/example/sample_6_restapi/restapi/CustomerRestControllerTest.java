package com.example.sample_6_restapi.restapi;//package com.example.sample_6_restapi.restapi;


import com.example.sample_6_restapi.domain.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
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
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql
public class CustomerRestControllerTest{
    @Autowired
    MockMvc mvc;

    /**
     * スタブ
     * @throws Exception
     */
    @Test
    public void getCustomers_list_動作確認() throws Exception {
        String result1= mvc.perform(get("/api/customers/list"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        String result2 = mvc.perform(get("/api/customers/list"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        assertEquals(result1, result2);
    }

    /**
     * スタブ
     * @throws Exception
     */
    @Test
    public void getCustomers_page_動作確認() throws Exception {
        String result1= mvc.perform(get("/api/customers/page"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        String result2 = mvc.perform(get("/api/customers/page"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        assertEquals(result1, result2);
    }

    @Test
    public void getCustome_動作確認() throws Exception {
        mvc.perform(get("/api/customers/{id}","1"))
           .andExpect(status().is2xxSuccessful())
           .andExpect(content().string(containsString("1")))
           .andExpect(content().string(not(containsString("4."))));
    }

    @Autowired
    ObjectMapper mapper;
    @Test
    public void postCustomers_動作確認() throws Exception {
        mvc.perform(post("/api/customers")
                    .param("firstName", "s")
                    .param("lastName", "a")
                    .content(mapper.writeValueAsString(new Customer(1,"s","a")))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    )
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().string(containsString("s")))
            .andExpect(content().string(containsString("a")))
            .andExpect(content().string(not(containsString("6."))));
    }

    @Test
    public void putCustomer() throws Exception {

        String firstName = "test1";
        String lastName = "test1";

        mvc.perform(put("/api/customers/update/{id}","1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .content(mapper.writeValueAsString(new Customer(1,firstName,lastName)))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().string(containsString(firstName)))
            .andExpect(content().string(containsString(lastName)))
            .andExpect(content().string(not(containsString("6."))));

    }

    //    まだ動かない
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