package com.example.autoTest.vehicle.dto;

public class OwnerDto {
  private long ownerid;
  private String firstname, lastname;

  public OwnerDto(){
    super();
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
}
