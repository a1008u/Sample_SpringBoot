package com.example.autoTest.doma_vehicle.domain;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "doma_owner")
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final long ownerid;
  @Column
  private final String firstname, lastname;
  @Column(name = "car_id")
  private final long carId;

//  public Owner(String firstname, String lastname, long carId) {
//    this.firstname = firstname;
//    this.lastname = lastname;
//    this.carId = carId;
//  }

  public Owner(long ownerid, String firstname, String lastname, long carId) {
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
