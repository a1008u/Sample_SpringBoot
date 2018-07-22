package com.example.autoTest.doma_vehicle.dao;

import com.example.autoTest.doma_vehicle.domain.Owner;
import com.example.autoTest.doma_vehicle.domain.OwnerCar;
import org.junit.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.runner.RunWith;
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.condition.OS.MAC;


@RunWith(SpringRunner.class)
@Import(DomaAutoConfiguration.class)
@JdbcTest
@ComponentScan
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"schema.sql", "data.sql"})
public class OwnerDaoTest {

  @Autowired
  private OwnerDao ownerDao;

  @Test
  public void selectAllOnlyOwnerの確認() {
    List<Owner> actualOwnerList = ownerDao.selectAllOnlyOwner();

    assertEquals(actualOwnerList.size(), 2);
    IntStream
      .range(0, actualOwnerList.size())
      .forEach(i -> {
        if (i == 0) {
          Owner except1owner = new Owner(0, "firsttest", "lasttest", 0);
          assertAll("except1owner"
            , () -> assertEquals(actualOwnerList.get(i).getCarId(), except1owner.getCarId())
            , () -> assertEquals(actualOwnerList.get(i).getFirstname(), except1owner.getFirstname())
            , () -> assertEquals(actualOwnerList.get(i).getFirstname(), except1owner.getFirstname())
            , () -> assertEquals(actualOwnerList.get(i).getLastname(), except1owner.getLastname())
            , () -> assertEquals(actualOwnerList.get(i).getOwnerid(), except1owner.getOwnerid())
          );

        }
        if (i == 1) {
          Owner except2owner = new Owner(1, "firsttest", "lasttest", 1);
          assertAll("except2owner"
            , () -> assertEquals(actualOwnerList.get(i).getCarId(), except2owner.getCarId())
            , () -> assertEquals(actualOwnerList.get(i).getFirstname(), except2owner.getFirstname())
            , () -> assertEquals(actualOwnerList.get(i).getLastname(), except2owner.getLastname())
            , () -> assertEquals(actualOwnerList.get(i).getOwnerid(), except2owner.getOwnerid())
          );
        }
      });
  }

  @Test
  public void selectAllの確認() {
    List<OwnerCar> actualOwnerCarList = ownerDao.selectAll();
    assertEquals(2, actualOwnerCarList.size());
  }

  @Test
  @EnabledOnOs(MAC)
  public void insertの確認() {

    Owner exceptOwner = new Owner(0, "firsttest", "lasttest", 0);
    Owner actualOwner = ownerDao.insert(exceptOwner).getEntity();
    assertAll("except2owner"
      , () -> assertEquals(actualOwner.getCarId(), exceptOwner.getCarId())
      , () -> assertEquals(actualOwner.getFirstname(), exceptOwner.getFirstname())
      , () -> assertEquals(actualOwner.getLastname(), exceptOwner.getLastname())
      , () -> assertEquals(actualOwner.getOwnerid(), exceptOwner.getOwnerid())
    );


}

}
