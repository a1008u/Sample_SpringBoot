package com.example.autoTest.vehicle.service;

import com.example.autoTest.vehicle.bean.CarBean;

import com.example.autoTest.vehicle.domain.Car;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"drop_schema.sql", "schema.sql","CarServiceTest.sql"})
public class CarServiceTest {

  @Autowired
  private CarService carService;

  @Test
  @DisplayName("😁")
  public void findAllの確認() {

    // exe
    List<CarBean> carList = carService.findAll();

    carList.forEach(car -> {
      assertAll("car",
        () -> assertEquals("firsttest", car.getOwnerbean().getFirstname())
        , () -> assertEquals("lasttest", car.getOwnerbean().getLastname())
        , () -> assertEquals("testbrand", car.getBrand())
        , () -> assertEquals("testcolor", car.getColor())
        , () -> assertEquals("testmodel", car.getModel())
        , () -> assertEquals("testnumber", car.getRegisterNumber())
        , () -> assertEquals(2018, car.getYear())
        , () -> assertEquals(8102, car.getPrice())
      );
    });
  }

  @Test
  @DisplayName("😁")
  public void findByBrandの確認() {

    CarBean carBean = new CarBean();
    carBean.setBrand("testbrand");

    // exe
    List<CarBean> carList = carService.findByBrand(carBean);

    carList.forEach(car -> {
      assertAll("car",
        () -> assertEquals("firsttest", car.getOwnerbean().getFirstname())
        , () -> assertEquals("lasttest", car.getOwnerbean().getLastname())
        , () -> assertEquals("testbrand", car.getBrand())
        , () -> assertEquals("testcolor", car.getColor())
        , () -> assertEquals("testmodel", car.getModel())
        , () -> assertEquals("testnumber", car.getRegisterNumber())
        , () -> assertEquals(2018, car.getYear())
        , () -> assertEquals(8102, car.getPrice())
      );
    });
  }

}
