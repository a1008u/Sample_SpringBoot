package com.example.autoTest.doma_vehicle.domain;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "doma_car")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final long id;
  @Column
  private final  String brand, model, color;
  @Column(name = "register_number")
  private final String registerNumber;
  @Column
  private final  int year, price;
  @Column(name = "owner_id")
  private final long ownerId;

//  public Car( String brand, String model, String color, String registerNumber, int year, int price, long ownerId) {;
//    this.brand = brand;
//    this.model = model;
//    this.color = color;
//    this.registerNumber = registerNumber;
//    this.year = year;
//    this.price = price;
//    this.ownerId = ownerId;
//  }

  public Car(long id, String brand, String model, String color, String registerNumber, int year, int price, long ownerId) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.registerNumber = registerNumber;
    this.year = year;
    this.price = price;
    this.ownerId = ownerId;
  }

  public long getId() {
    return id;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public String getColor() {
    return color;
  }

  public String getRegisterNumber() {
    return registerNumber;
  }

  public int getYear() {
    return year;
  }

  public int getPrice() {
    return price;
  }

  public long getOwnerId() {
    return ownerId;
  }
}
