package com.example.autoTest.doma_vehicle.bean;

public class DomaOwnerCarBean {

  private final long ownerid;
  private final String firstname, lastname;
  private final long carId;
  private final DomaCarBean domaCarBean;

  public DomaOwnerCarBean(long ownerid, String firstname, String lastname, long carId, DomaCarBean domaCarBean) {
    this.ownerid = ownerid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.carId = carId;
    this.domaCarBean = domaCarBean;
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

  public DomaCarBean getDomaCarBean() {
    return domaCarBean;
  }
}
