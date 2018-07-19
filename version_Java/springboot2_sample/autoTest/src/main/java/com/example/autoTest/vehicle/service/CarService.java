package com.example.autoTest.vehicle.service;

import com.example.autoTest.vehicle.bean.CarBean;
import com.example.autoTest.vehicle.bean.OwnerBean;
import com.example.autoTest.vehicle.domain.Car;
import com.example.autoTest.vehicle.domain.Owner;
import com.example.autoTest.vehicle.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarService {

  private CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  // Crud -----------------------------------------------
  public CarBean create(CarBean carBean) {

    Car car = new Car(
            carBean.getBrand()
            , carBean.getModel()
            , carBean.getColor()
            , carBean.getRegisterNumber()
            , carBean.getYear()
            , carBean.getPrice()
            , new Owner(
                    carBean.getOwnerbean().getFirstname()
                    , carBean.getOwnerbean().getLastname()
                    , null)
    );

    Car registCar = carRepository.save(car);

    return new CarBean(
            registCar.getId()
            , registCar.getBrand()
            , registCar.getModel()
            , registCar.getColor()
            , registCar.getRegisterNumber()
            , registCar.getYear()
            , registCar.getPrice()
            , new OwnerBean(registCar.getOwner().getOwnerid()
            , registCar.getOwner().getFirstname()
            , registCar.getOwner().getLastname()
            , null)
    );
  }

  // cRud -----------------------------------------------
  public List<CarBean> findAll() {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findAll()
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByBrand(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByBrand(carBean.getBrand())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByColor(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByColor(carBean.getColor())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByYear(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByYear(carBean.getYear())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByBrandAndModel(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByBrandAndModel(carBean.getBrand(), carBean.getModel())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByBrandOrColor(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByBrandOrColor(carBean.getBrand(), carBean.getColor())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByBrandOrderByYearAsc(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByBrandOrderByYearAsc(carBean.getBrand())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }

  public List<CarBean> findByBrand2(CarBean carBean) {

    List<CarBean> carBeanList = new ArrayList<>();
    carRepository
      .findByBrand2(carBean.getBrand())
      .forEach(car -> makeBean(carBeanList, car));
    return carBeanList;
  }


  private void makeBean(List<CarBean> carBeanList, Car car) {
    CarBean tmpcarBean = new CarBean(car.getId()
      , car.getBrand()
      , car.getModel()
      , car.getColor()
      , car.getRegisterNumber()
      , car.getYear()
      , car.getPrice()
      , new OwnerBean(
              car.getOwner().getOwnerid()
            , car.getOwner().getFirstname()
            , car.getOwner().getLastname()
            , null));
    carBeanList.add(tmpcarBean);
  }
}
