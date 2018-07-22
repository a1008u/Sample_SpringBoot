package com.example.autoTest.doma_vehicle.dto;


import com.example.autoTest.vehicle.dto.OwnerDto;

public class DomaCarOwnerDto {

  private final long id;
  private final  String brand, model, color;
  private final String registerNumber;
  private final  int year, price;
  private final long ownerId;
  private final OwnerDto ownerdto;

  public DomaCarOwnerDto(long id, String brand, String model, String color, String registerNumber, int year, int price, long ownerId, OwnerDto ownerdto) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.registerNumber = registerNumber;
    this.year = year;
    this.price = price;
    this.ownerId = ownerId;
    this.ownerdto = ownerdto;
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

  public OwnerDto getOwnerdto() {
    return ownerdto;
  }
}
