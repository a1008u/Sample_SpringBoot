package com.example.autoTest.doma_vehicle.restController;

import com.example.autoTest.doma_vehicle.bean.DomaCarBean;

import com.example.autoTest.doma_vehicle.bean.DomaCarOwnerBean;
import com.example.autoTest.doma_vehicle.dto.DomaCarOwnerDto;
import com.example.autoTest.doma_vehicle.service.DomaCarService;
import com.example.autoTest.vehicle.dto.CarDto;
import com.example.autoTest.vehicle.dto.OwnerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DomaCarController {

  private final DomaCarService domaCarService;

  public DomaCarController(DomaCarService domaCarService) {
    this.domaCarService = domaCarService;
  }

  /**
   * cRud -------------------------------------------------- <br />
   * @return
   */
  @GetMapping("/doma/car/findall")
  public List<DomaCarOwnerDto> selectAll() {

    List<DomaCarOwnerDto> carDtoList = new ArrayList<>();
    domaCarService
      .selectAll()
      .forEach(domaCarBean -> {
        DomaCarOwnerDto domaCarOwnerDto = new DomaCarOwnerDto(
          domaCarBean.getId()
          , domaCarBean.getBrand()
          , domaCarBean.getModel()
          , domaCarBean.getColor()
          , domaCarBean.getRegisterNumber()
          , domaCarBean.getYear()
          , domaCarBean.getPrice()
          , domaCarBean.getOwnerId()
          , new OwnerDto(
          domaCarBean.getDomaOwnerBean().getOwnerid()
          , domaCarBean.getDomaOwnerBean().getFirstname()
          , domaCarBean.getDomaOwnerBean().getLastname()
        )

        );
        carDtoList.add(domaCarOwnerDto);
      });
    return carDtoList;
  }

  @GetMapping("/doma/car/findOnlyCar")
  public List<CarDto> selectOnlyCar() {

    List<CarDto> carDtoList = new ArrayList<>();
    domaCarService
      .selectAllOnlyCar()
      .forEach(domaCarBean -> {
        CarDto carDto = new CarDto(
          domaCarBean.getId()
          , domaCarBean.getBrand()
          , domaCarBean.getModel()
          , domaCarBean.getColor()
          , domaCarBean.getRegisterNumber()
          , domaCarBean.getYear()
          , domaCarBean.getPrice()
        );
      carDtoList.add(carDto);
    });
    return carDtoList;
  }

  @GetMapping("/doma/car/findAllStream")
  public List<DomaCarOwnerDto> selectAllStream() {

    List<DomaCarOwnerDto> domaCarOwnerDtoList = new ArrayList<>();
    domaCarService
      .selectAllStream()
      .map(domaCarOwnerBean -> new DomaCarOwnerDto(
        domaCarOwnerBean.getId()
        , domaCarOwnerBean.getBrand()
        , domaCarOwnerBean.getModel()
        , domaCarOwnerBean.getColor()
        , domaCarOwnerBean.getRegisterNumber()
        , domaCarOwnerBean.getYear()
        , domaCarOwnerBean.getPrice()
        , domaCarOwnerBean.getOwnerId()
        , new OwnerDto(
          domaCarOwnerBean.getDomaOwnerBean().getOwnerid()
          , domaCarOwnerBean.getDomaOwnerBean().getFirstname()
          , domaCarOwnerBean.getDomaOwnerBean().getLastname()
          )
        )
      )
      .forEach(domaCarOwnerDto -> domaCarOwnerDtoList.add(domaCarOwnerDto));
    return domaCarOwnerDtoList;
  }

  /**
   * Crud -------------------------------------------------- <br />
   * @param carDto
   * @return
   */
  @PostMapping("/doma/car/save")
  public CarDto insert(@RequestBody CarDto carDto) {
    DomaCarBean domaCarBean = new DomaCarBean(
            carDto.getId()
            , carDto.getBrand()
            , carDto.getModel()
            , carDto.getColor()
            , carDto.getRegisterNumber()
            , carDto.getYear()
            , carDto.getPrice()
            , carDto.getOwnerdto().getOwnerid()
    );

    DomaCarBean insertDomaCarBean = domaCarService.save(domaCarBean);
    CarDto insertCarDto = new CarDto(
            insertDomaCarBean.getId()
            , insertDomaCarBean.getBrand()
            , insertDomaCarBean.getModel()
            , insertDomaCarBean.getColor()
            , insertDomaCarBean.getRegisterNumber()
            , insertDomaCarBean.getYear()
            , insertDomaCarBean.getPrice()
    );
    return insertCarDto;
  }



}
