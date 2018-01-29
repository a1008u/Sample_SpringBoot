package com.example.sample_6_restapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
public class CustomerServiceTest {

    @Test
    public void findAll_正常(){

    }

    @Test
    public void findAll_異常(){

    }

    @Test
    public void findAll_Page_正常(){

    }

    @Test
    public void findAll_Page_異常(){

    }

    @Test
    public void findOne_正常(){

    }

    @Test
    public void findOne_異常(){

    }

    @Test
    public void create_正常(){

    }

    @Test
    public void create_異常(){

    }

    @Test
    public void update_正常(){

    }

    @Test
    public void update_異常() {

    }

    @Test
    public void delete_正常(){

    }

    @Test
    public void delete_異常() {

    }

}
