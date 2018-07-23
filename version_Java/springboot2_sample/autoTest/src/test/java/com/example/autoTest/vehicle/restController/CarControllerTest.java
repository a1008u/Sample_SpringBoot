package com.example.autoTest.vehicle.restController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"drop_schema.sql", "schema.sql","CarControllerTest.sql"})
public class CarControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void findAll() {

        String url = "http://localhost:"+ port + "/findall";
        List<Map<String, Object>> carDtoList = testRestTemplate.getForObject(url, ArrayList.class);


        System.out.println(carDtoList.toArray());

        carDtoList
            .forEach(carMap -> {
                assertThat("testbrand",is(carMap.get("brand")));
            });


    }

}
