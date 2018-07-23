package com.example.autoTest.vehicle.domain;

import javax.persistence.*;
import java.util.List;

@Table(name = "owner")
@Entity
public class Owner {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long ownerid;
  private String firstname, lastname;

  @OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
  private List<Car> cars;

  public Owner() {}

  public Owner(String firstname, String lastname, List<Car> cars) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
    this.cars = cars;
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
  public List<Car> getCars() {
    return cars;
  }
  public void setCars(List<Car> cars) {
    this.cars = cars;
  }
}
