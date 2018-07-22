package com.example.autoTest.doma_vehicle.bean;

public class DomaOwnerBean {

  private final long ownerid;
  private final String firstname, lastname;
  private final long carId;

  public DomaOwnerBean(long ownerid, String firstname, String lastname, long carId) {
    this.ownerid = ownerid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.carId = carId;
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
}
