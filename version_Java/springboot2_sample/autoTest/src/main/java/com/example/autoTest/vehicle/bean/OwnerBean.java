package com.example.autoTest.vehicle.bean;

public class OwnerBean {

  private long ownerid;
  private String firstname, lastname;

  public OwnerBean(long ownerid
          , String firstname
          , String lastname) {
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

}
