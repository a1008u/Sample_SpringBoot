package com.example.autoTest.vehicle.service;

import com.example.autoTest.vehicle.bean.CarBean;
import com.example.autoTest.vehicle.bean.OwnerBean;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"drop_schema.sql", "schema.sql"})
public class OwnerServiceTest {

    @Autowired
    public OwnerService ownerService;

    @Test
    @Tag("create")
    @EnabledOnOs(OS.MAC)
    @DisplayName("😁")
    public void createの確認() {

        List<CarBean> carBeanList = new ArrayList<>();
        CarBean sampleCarBean = new CarBean(
            1
            ,"brandTest"
            , "modelTest"
            , "colorTest"
            , "1234Test"
            , 2222
            , 3333
            , null);
        OwnerBean sampleOwnerBean = new OwnerBean(
                1
                ,"test"
                , "sample"
                , carBeanList);
        sampleCarBean.setOwnerbean(sampleOwnerBean);
        carBeanList.add(sampleCarBean);

        // exe
        OwnerBean ownerBean = ownerService.create(sampleOwnerBean);

        assertEquals("test", ownerBean.getFirstname());
        assertEquals("sample", ownerBean.getLastname());
        ownerBean
            .getCarBeanList()
            .forEach(carBean -> {
                assertAll("car"
                    , () -> assertThat("brandTest", is(carBean.getBrand()))
                    , () -> assertThat("colorTest", is(carBean.getColor()))
                    , () -> assertThat("modelTest", is(carBean.getModel()))
                    , () -> assertThat("1234Test", is(carBean.getRegisterNumber()))
                    , () -> assertThat(2222, is(carBean.getYear()))
                    , () -> assertThat(3333, is(carBean.getPrice()))
                );
            });

    }


}
