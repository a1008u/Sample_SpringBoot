package com.example.autoTest.vehicle.bean;

import java.util.List;

public class OwnerBean {

  private long ownerid;
  private String firstname, lastname;
  private List<CarBean> carBeanList;

  public OwnerBean(long ownerid
          , String firstname
          , String lastname
          , List<CarBean> carBeanList) {
    this.ownerid = ownerid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.carBeanList = carBeanList;
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

  public List<CarBean> getCarBeanList() {
    return carBeanList;
  }

  public void setCarBeanList(List<CarBean> carBeanList) {
    this.carBeanList = carBeanList;
  }
}
