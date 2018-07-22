package com.example.autoTest.vehicle.restController;


import com.example.autoTest.vehicle.bean.CarBean;
import com.example.autoTest.vehicle.bean.OwnerBean;
import com.example.autoTest.vehicle.dto.CarDto;
import com.example.autoTest.vehicle.dto.OwnerDto;
import com.example.autoTest.vehicle.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

  private CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping(path = "/create/cars")
  public ResponseEntity<CarDto> getCars(
          @RequestBody CarDto carDto
          , UriComponentsBuilder uriBuilder) {

    CarBean carBean = new CarBean(
            carDto.getId()
            , carDto.getBrand()
            , carDto.getModel()
            , carDto.getColor()
            , carDto.getRegisterNumber()
            , carDto.getYear()
            , carDto.getPrice()
            , new OwnerBean(
                    carDto.getOwnerdto().getOwnerid()
                    , carDto.getOwnerdto().getFirstname()
                    , carDto.getOwnerdto().getLastname()
    ,null)
    );
    CarBean createCarBean = carService.create(carBean);
    CarDto carDto1 = new CarDto(
            createCarBean.getId()
            , createCarBean.getBrand()
            , createCarBean.getModel()
            , createCarBean.getColor()
            , createCarBean.getRegisterNumber()
            , createCarBean.getYear()
            , createCarBean.getPrice()
            , new OwnerDto(createCarBean.getOwnerbean().getOwnerid()
            , createCarBean.getOwnerbean().getFirstname()
            , createCarBean.getOwnerbean().getLastname(), null)
    );
    URI location = uriBuilder.path("/findall").buildAndExpand().toUri();
    return ResponseEntity.created(location).body(carDto1);
  }

  @RequestMapping("/findall")
  public List<CarDto> findAll() {

    List<CarDto> carDtoList = new ArrayList<>();
    carService
      .findAll()
      .forEach(carBean -> {
        CarDto carDto = new CarDto(
                carBean.getId()
          , carBean.getBrand()
          , carBean.getModel()
          , carBean.getColor()
          , carBean.getRegisterNumber()
          , carBean.getYear()
          , carBean.getPrice()
          , new OwnerDto(
                  carBean.getOwnerbean().getOwnerid()
                , carBean.getOwnerbean().getFirstname()
                , carBean.getOwnerbean().getLastname(), null));
        carDtoList.add(carDto);
      });

    return carDtoList;
  }
}
