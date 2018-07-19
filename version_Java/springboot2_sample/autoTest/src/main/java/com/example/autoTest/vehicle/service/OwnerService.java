package com.example.autoTest.vehicle.service;

import com.example.autoTest.vehicle.bean.CarBean;
import com.example.autoTest.vehicle.bean.OwnerBean;
import com.example.autoTest.vehicle.domain.Car;
import com.example.autoTest.vehicle.domain.Owner;
import com.example.autoTest.vehicle.repository.CarRepository;
import com.example.autoTest.vehicle.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

  private OwnerRepository ownerepository;

  public OwnerService(OwnerRepository ownerepository) {
    this.ownerepository = ownerepository;
  }

  // Crud -----------------------------------------------
  public OwnerBean create(OwnerBean ownerBean) {

    List<Car> cars = new ArrayList<>();

    ownerBean
      .getCarBeanList()
      .forEach(carBean -> {
        Car car = new Car(
                carBean.getBrand()
                , carBean.getModel()
                , carBean.getColor()
                , carBean.getRegisterNumber()
                , carBean.getYear()
                , carBean.getPrice()
                , null);
        cars.add(car);
    });

    Owner owner = new Owner(
            ownerBean.getFirstname()
            , ownerBean.getLastname()
            , cars
    );

    Owner registOwner =  ownerepository.save(owner);

    List<CarBean>  registCars = new ArrayList<>();

    registOwner.getCars().forEach(car -> {
      CarBean carBean = new CarBean(
        car.getId()
              , car.getBrand()
              , car.getModel()
              , car.getColor()
              , car.getRegisterNumber()
              , car.getYear()
              , car.getPrice()
              , null
      );
      registCars.add(carBean);
    });



    return new OwnerBean(
            registOwner.getOwnerid()
            , registOwner.getFirstname()
            , registOwner.getLastname()
            , registCars
    );
  }
}
