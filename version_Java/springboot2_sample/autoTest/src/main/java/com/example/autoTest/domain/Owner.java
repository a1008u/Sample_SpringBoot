package com.example.autoTest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long ownerid;
  private String firstname, lastname;

  public Owner() {}

  public Owner(String firstname, String lastname) {
    super();
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
