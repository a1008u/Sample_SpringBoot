package com.example.autoTest.repository;

import com.example.autoTest.domain.Car;
import com.example.autoTest.domain.Owner;
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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@Sql
public class CarRepositoryTest {

  @Autowired
  CarRepository carRepository;

  @Autowired
  OwnerRepository ownerRepository;

  @Test
  @DisplayName("😁")
  public void 起動確認() {

    // exe
    List<Car> carList = carRepository.findByBrand("testbrand");

    carList.forEach(car -> {
      assertAll("car",
        () -> assertEquals("firsttest", car.getOwner().getFirstname())
        , () -> assertEquals("lasttest", car.getOwner().getLastname())
        , () -> assertEquals("testbrand", car.getBrand())
        , () -> assertEquals("testcolor", car.getColor())
        , () -> assertEquals("testmodel", car.getModel())
        , () -> assertEquals("testnumber", car.getRegisterNumber())
        , () -> assertEquals(2018, car.getYear())
        , () -> assertEquals(8102, car.getPrice())
      );
    });

    // confirm
//    carList.forEach(car -> {
//      assertThat(car.getBrand(), is("testbrand"));
//      assertThat(car.getColor(), is("testcolor"));
//      assertThat(car.getModel(), is("testmodel"));
//      assertThat(car.getOwner().getFirstname(), is("firsttest"));
//      assertThat(car.getOwner().getLastname(), is("lasttest"));
//      assertThat(car.getRegisterNumber(), is("testnumber"));
//      assertThat(car.getYear(), is(2018));
//      assertThat(car.getPrice(), is(8102));
//    });


  }

}
