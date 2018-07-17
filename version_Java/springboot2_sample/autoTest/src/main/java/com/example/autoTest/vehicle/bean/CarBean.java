package com.example.autoTest.vehicle.bean;

import com.example.autoTest.vehicle.domain.Owner;

public class CarBean {

  private long id;
  private String brand, model, color, registerNumber;
  private int year, price;

  private OwnerBean ownerbean;

  public CarBean(){}

  public CarBean(long id
          , String brand
          , String model
          , String color
          , String registerNumber
          , int year
          , int price
          , OwnerBean ownerbean) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.registerNumber = registerNumber;
    this.year = year;
    this.price = price;
    this.ownerbean = new OwnerBean(
            ownerbean.getOwnerid()
            , ownerbean.getFirstname()
            , ownerbean.getLastname());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getRegisterNumber() {
    return registerNumber;
  }

  public void setRegisterNumber(String registerNumber) {
    this.registerNumber = registerNumber;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public OwnerBean getOwnerbean() {
    return ownerbean;
  }

  public void setOwnerbean(OwnerBean ownerbean) {
    this.ownerbean = ownerbean;
  }
}
