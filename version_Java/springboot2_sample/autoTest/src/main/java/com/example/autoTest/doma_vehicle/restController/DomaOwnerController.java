package com.example.autoTest.doma_vehicle.restController;

import com.example.autoTest.doma_vehicle.bean.DomaOwnerBean;
import com.example.autoTest.doma_vehicle.domain.OwnerCar;
import com.example.autoTest.doma_vehicle.dto.DomaOwnerCarDto;
import com.example.autoTest.doma_vehicle.service.DomaOwnerService;
import com.example.autoTest.vehicle.dto.CarDto;
import com.example.autoTest.vehicle.dto.OwnerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DomaOwnerController {

  private final DomaOwnerService domaownerService;


  public DomaOwnerController(DomaOwnerService domaownerService) {
    this.domaownerService = domaownerService;
  }

  /**
   * cRud -------------------------------------------------- <br />
   * @return
   */
  @GetMapping("/doma/owner/findall")
  public List<DomaOwnerCarDto> selectAll() {

    List<DomaOwnerCarDto> domaOwnerCarDtoList = new ArrayList<>();
    domaownerService.selectAll().forEach(domaCarOwnerBean -> {
      DomaOwnerCarDto domaOwnerCarDto = new DomaOwnerCarDto(
              domaCarOwnerBean.getOwnerid()
              , domaCarOwnerBean.getFirstname()
              , domaCarOwnerBean.getLastname()
              , domaCarOwnerBean.getCarId()
              , new CarDto(
                domaCarOwnerBean.getDomaCarBean().getId()
              , domaCarOwnerBean.getDomaCarBean().getBrand()
              , domaCarOwnerBean.getDomaCarBean().getModel()
              , domaCarOwnerBean.getDomaCarBean().getColor()
              , domaCarOwnerBean.getDomaCarBean().getRegisterNumber()
              , domaCarOwnerBean.getDomaCarBean().getYear()
              , domaCarOwnerBean.getDomaCarBean().getPrice()
      )
      );
      domaOwnerCarDtoList.add(domaOwnerCarDto);
    });
    return domaOwnerCarDtoList;
  }

  @GetMapping("/doma/owner/findallOnlyOwner")
  public List<OwnerDto> selectAllOnlyOwner() {
    List<OwnerDto> ownerDtoList = new ArrayList<>();
    domaownerService
      .selectAllOnlyOwner()
      .forEach(domadomaOwnerBeanBean -> {
        OwnerDto ownerDto = new OwnerDto(
                domadomaOwnerBeanBean.getOwnerid()
                , domadomaOwnerBeanBean.getFirstname()
                , domadomaOwnerBeanBean.getLastname()
        );
        ownerDtoList.add(ownerDto);
      });
    return ownerDtoList;
  }

  /**
   * Crud -------------------------------------------------- <br />
   * @param ownerDto
   * @return
   */
  @PostMapping("/doma/owner/save")
  public OwnerDto insert(@RequestBody OwnerDto ownerDto) {

    DomaOwnerBean domaOwnerBean = new DomaOwnerBean(
            ownerDto.getOwnerid()
            , ownerDto.getFirstname()
            , ownerDto.getLastname()
            , 1
    );
    DomaOwnerBean insertDomaOwnerBean = domaownerService.save(domaOwnerBean);
    OwnerDto insertOwnerDto = new OwnerDto(
            insertDomaOwnerBean.getOwnerid()
            , insertDomaOwnerBean.getFirstname()
            , insertDomaOwnerBean.getLastname()
    );
    return insertOwnerDto;
  }

}
