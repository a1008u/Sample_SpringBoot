package com.example.autoTest.vehicle.dto;

import java.util.List;

public class OwnerDto {
  private long ownerid;
  private String firstname, lastname;
  List<CarDto> carDtoList;

  public OwnerDto(){
    super();
  }

  public OwnerDto(long ownerid, String firstname, String lastname, List<CarDto> carDtoList) {
    this.ownerid = ownerid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.carDtoList = carDtoList;
  }

  public OwnerDto(long ownerid, String firstname, String lastname) {
    this.ownerid = ownerid;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public long getOwnerid() {
    return ownerid;
  }

  public void setOwnerid(long ownerid) {
    this.ownerid = ownerid;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public List<CarDto> getCarDtoList() {
    return carDtoList;
  }

  public void setCarDtoList(List<CarDto> carDtoList) {
    this.carDtoList = carDtoList;
  }
}
