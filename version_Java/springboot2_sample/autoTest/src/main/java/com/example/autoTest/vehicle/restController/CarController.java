package com.example.autoTest.vehicle.restController;


import com.example.autoTest.vehicle.dto.CarDto;
import com.example.autoTest.vehicle.service.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

  private CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @RequestMapping("/cars")
  public List<CarDto> getCars() {

    List<CarDto> carDtoList = new ArrayList<>();
    carService
      .findAll()
      .forEach(carBean -> {
        CarDto carDto = new CarDto(carBean.getId()
          , carBean.getBrand()
          , carBean.getModel()
          , carBean.getColor()
          , carBean.getRegisterNumber()
          , carBean.getYear()
          , carBean.getPrice()
          , carBean.getOwnerbean());
        carDtoList.add(carDto);
      });

    return carDtoList;
  }
}
