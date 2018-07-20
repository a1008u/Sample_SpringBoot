package com.example.autoTest.vehicle.repository;

import com.example.autoTest.vehicle.domain.Car;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"CarRepositoryTest.sql"})
@DataJpaTest
public class CarRepositoryTest {

  @Autowired
  CarRepository carRepository;

  @Autowired
  OwnerRepository ownerRepository;

  @Test
  @DisplayName("😁")
  public void findAllの確認() {

    // exe
    Iterable<Car> carList = carRepository.findAll();

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

  @Test
  @DisplayName("😁")
  public void findByBrandの確認() {

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
  }

  @Test
  @DisplayName("😁")
  public void findByColorの確認() {

    // exe
    List<Car> carList = carRepository.findByColor("testcolor");

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
  }

  @Test
  @DisplayName("😁")
  public void findByYearの確認() {

    // exe
    List<Car> carList = carRepository.findByYear(2018);

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
  }

  @Test
  @DisplayName("😁")
  public void findByBrandAndModelの確認() {

    // exe
    List<Car> carList = carRepository.findByBrandAndModel("testbrand","testmodel");

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
  }

  @Test
  @DisplayName("😁")
  public void findByBrandOrColorの確認() {

    // exe
    List<Car> carList = carRepository.findByBrandOrColor("testbrand","testcolor");

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
  }

  @Test
  @DisplayName("😁")
  public void findByBrandOrderByYearAscの確認() {

    // exe
    List<Car> carList = carRepository.findByBrandOrderByYearAsc("testbrand");

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
  }

  @Test
  @DisplayName("😁")
  public void findByBrand2の確認() {

    // exe
    List<Car> carList = carRepository.findByBrand2("testbrand");

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
  }

}
