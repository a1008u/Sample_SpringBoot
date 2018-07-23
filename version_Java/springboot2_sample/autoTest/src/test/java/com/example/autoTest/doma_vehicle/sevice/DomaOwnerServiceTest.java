package com.example.autoTest.doma_vehicle.sevice;

import com.example.autoTest.doma_vehicle.bean.DomaOwnerBean;
import com.example.autoTest.doma_vehicle.bean.DomaOwnerCarBean;
import com.example.autoTest.doma_vehicle.service.DomaOwnerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Import(DomaAutoConfiguration.class)
@SpringBootTest
@ComponentScan
@ActiveProfiles("unit")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"schema.sql"})
public class DomaOwnerServiceTest {

  @Autowired
  DomaOwnerService domaOwnerService;

  @Test
  @Sql({ "selectAllOnlyOwner.sql"})
  public void selectAllOnlyOwnerの確認() {

    List<DomaOwnerBean> actualDomaOwnerBeanList = domaOwnerService.selectAllOnlyOwner();
    assertEquals(2, actualDomaOwnerBeanList.size());

    IntStream
      .range(0, actualDomaOwnerBeanList.size())
      .forEach(i -> {

        if (i == 0) {
          DomaOwnerBean domaOwnerBean = new DomaOwnerBean(0,"firsttest", "lasttest" ,0);
          assertAll("domaOwnerBean_1"
            ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getOwnerid(), domaOwnerBean.getOwnerid())
            ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getFirstname(), domaOwnerBean.getFirstname())
            ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getLastname(), domaOwnerBean.getLastname())
            ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getCarId(), domaOwnerBean.getCarId())
          );
        }

        if (i == 1) {
          DomaOwnerBean domaOwnerBean = new DomaOwnerBean(1,"firsttest", "lasttest" ,1);
          assertAll("domaOwnerBean_2"
                  ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getOwnerid(), domaOwnerBean.getOwnerid())
                  ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getFirstname(), domaOwnerBean.getFirstname())
                  ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getLastname(), domaOwnerBean.getLastname())
                  ,() -> assertEquals( actualDomaOwnerBeanList.get(i).getCarId(), domaOwnerBean.getCarId())
          );
        }

      });
  }

  @Test
  @Sql({ "selectAll.sql"})
  public void selectAllの確認() {
    List<DomaOwnerCarBean> domaOwnerCarBeanList = domaOwnerService.selectAll();
    assertEquals(2, domaOwnerCarBeanList.size());
  }

  @Test
  public void savaの確認() {

    DomaOwnerBean expectDomaOwnerBean = new DomaOwnerBean(3, "firstbean", "lastabean", 3);
    DomaOwnerBean actualDomaOwnerBean = domaOwnerService.save(expectDomaOwnerBean);
    assertAll(
      "doma_save"
      , () -> assertEquals(expectDomaOwnerBean.getOwnerid(), actualDomaOwnerBean.getOwnerid())
      , () -> assertEquals(expectDomaOwnerBean.getFirstname(), actualDomaOwnerBean.getFirstname())
      , () -> assertEquals(expectDomaOwnerBean.getLastname(), actualDomaOwnerBean.getLastname())
      , () -> assertEquals(expectDomaOwnerBean.getCarId(), actualDomaOwnerBean.getCarId())
    );

  }

}
