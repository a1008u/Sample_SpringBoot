package com.example.autoTest.doma_vehicle.service;

import com.example.autoTest.doma_vehicle.bean.DomaCarBean;
import com.example.autoTest.doma_vehicle.bean.DomaCarOwnerBean;
import com.example.autoTest.doma_vehicle.bean.DomaOwnerBean;
import com.example.autoTest.doma_vehicle.bean.DomaOwnerCarBean;
import com.example.autoTest.doma_vehicle.dao.CarDao;
import com.example.autoTest.doma_vehicle.domain.Car;
import com.example.autoTest.doma_vehicle.domain.OwnerCar;
import org.seasar.doma.jdbc.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class DomaCarService {

  private final CarDao carDao;

  public DomaCarService(CarDao carDao) {
    this.carDao = carDao;
  }

  // cRud -------------------------------------------------------
  public List<DomaCarOwnerBean> selectAll() {
    List<DomaCarOwnerBean> domaCarOwnerBeanList = new ArrayList<>();
    List<OwnerCar> ownerCarList = carDao.selectAll();
    ownerCarList.forEach(ownerCar -> {
      DomaCarOwnerBean domaCarOwnerBean = new DomaCarOwnerBean(
              ownerCar.getId()
              , ownerCar.getBrand()
              , ownerCar.getColor()
              , ownerCar.getColor()
              , ownerCar.getRegisterNumber()
              , ownerCar.getYear()
              , ownerCar.getPrice()
              , ownerCar.getOwnerId()
              , new DomaOwnerBean(
              ownerCar.getOwnerid()
              , ownerCar.getFirstname()
              , ownerCar.getLastname()
              , ownerCar.getCarId()
      )
      );
      domaCarOwnerBeanList.add(domaCarOwnerBean);
    });

    return domaCarOwnerBeanList;
  }

  public List<DomaCarBean> selectAllOnlyCar() {
    List<DomaCarBean> domaCarBeanList = new ArrayList<>();
    carDao
            .selectAllOnlyCar()
            .forEach(car -> {
              DomaCarBean domaCarBean = new DomaCarBean(
                      car.getId()
                      , car.getBrand()
                      , car.getColor()
                      , car.getModel()
                      , car.getRegisterNumber()
                      , car.getYear()
                      , car.getPrice()
                      , car.getOwnerId()
              );
              domaCarBeanList.add(domaCarBean);
            });
    return domaCarBeanList;
  }

  public Stream<DomaCarOwnerBean> selectAllStream() {

    List<DomaCarOwnerBean> domaOwnerCarBeanList = new ArrayList<>();
    carDao
      .selectAllStream()
      .map(ownerCar -> {
        DomaCarOwnerBean domaCarOwnerBean = new DomaCarOwnerBean(
          ownerCar.getId()
          , ownerCar.getBrand()
          , ownerCar.getColor()
          , ownerCar.getColor()
          , ownerCar.getRegisterNumber()
          , ownerCar.getYear()
          , ownerCar.getPrice()
          , ownerCar.getOwnerId()
          , new DomaOwnerBean(
              ownerCar.getOwnerid()
              , ownerCar.getFirstname()
              , ownerCar.getLastname()
              , ownerCar.getCarId()
          )
        );
        return domaCarOwnerBean;
      }).forEach(domaCarOwnerBean -> domaOwnerCarBeanList.add(domaCarOwnerBean));
    return domaOwnerCarBeanList.stream();
  }

  // Crud -------------------------------------------------------
  public DomaCarBean save(DomaCarBean domaCarBean) {

    Car car = new Car(
            domaCarBean.getId()
            , domaCarBean.getBrand()
            , domaCarBean.getModel()
            , domaCarBean.getColor()
            , domaCarBean.getRegisterNumber()
            , domaCarBean.getYear()
            , domaCarBean.getPrice()
            , domaCarBean.getOwnerId()
    );
    Result<Car> insertCar = carDao.insert(car);
    DomaCarBean InsertDomaCarBean = new DomaCarBean(
            insertCar.getEntity().getId()
            , insertCar.getEntity().getBrand()
            , insertCar.getEntity().getModel()
            , insertCar.getEntity().getColor()
            , insertCar.getEntity().getRegisterNumber()
            , insertCar.getEntity().getYear()
            , insertCar.getEntity().getPrice()
            , insertCar.getEntity().getOwnerId()
    );

    return InsertDomaCarBean;

  }
}
