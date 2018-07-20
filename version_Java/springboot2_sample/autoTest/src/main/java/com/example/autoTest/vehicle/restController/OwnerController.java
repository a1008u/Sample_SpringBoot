package com.example.autoTest.vehicle.restController;

import com.example.autoTest.vehicle.bean.CarBean;
import com.example.autoTest.vehicle.bean.OwnerBean;
import com.example.autoTest.vehicle.dto.CarDto;
import com.example.autoTest.vehicle.dto.OwnerDto;
import com.example.autoTest.vehicle.service.OwnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OwnerController {

  public OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @PostMapping(path = "/create/owner")
  public OwnerDto getCars(
          @RequestBody OwnerDto ownerDto
          , UriComponentsBuilder uriBuilder) {

    List<CarBean> carBeanList = new ArrayList<>();
    ownerDto.getCarDtoList().forEach(carDto -> {
      CarBean carBean = new CarBean(
              carDto.getId()
              , carDto.getBrand()
              , carDto.getModel()
              , carDto.getColor()
              , carDto.getRegisterNumber()
              , carDto.getYear()
              , carDto.getPrice()
              , null
      );
      carBeanList.add(carBean);
    });

   OwnerBean ownerBean = new OwnerBean(
           ownerDto.getOwnerid()
           , ownerDto.getFirstname()
           , ownerDto.getLastname()
           , carBeanList
    );

   OwnerBean ownerBean1 = ownerService.create(ownerBean);

    //CarBean createCarBean = carService.create(carBean);

    List<CarDto> carDtoList = new ArrayList<>();
    ownerBean1.getCarBeanList().forEach(carDto -> {
      CarDto carDto1 = new CarDto(
              carDto.getId()
              , carDto.getBrand()
              , carDto.getModel()
              , carDto.getColor()
              , carDto.getRegisterNumber()
              , carDto.getYear()
              , carDto.getPrice()
              , null
      );
      carDtoList.add(carDto1);
    });

    OwnerDto ownerDto1 = new OwnerDto(
            ownerBean1.getOwnerid()
            , ownerBean1.getFirstname()
            , ownerBean1.getLastname()
            , carDtoList
    );

    return ownerDto1;
  }

}
