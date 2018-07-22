package com.example.autoTest.doma_vehicle.dto;

import com.example.autoTest.doma_vehicle.bean.DomaCarBean;
import com.example.autoTest.vehicle.dto.CarDto;

public class DomaOwnerCarDto {

  private final long ownerid;
  private final String firstname, lastname;
  private final long carId;
  private final CarDto carDto;

  public DomaOwnerCarDto(long ownerid, String firstname, String lastname, long carId, CarDto carDto) {
    this.ownerid = ownerid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.carId = carId;
    this.carDto = carDto;
  }

  public long getOwnerid() {
    return ownerid;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public long getCarId() {
    return carId;
  }

  public CarDto getCarDto() {
    return carDto;
  }
}
