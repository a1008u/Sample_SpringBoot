package com.example.autoTest.doma_vehicle.service;

import com.example.autoTest.doma_vehicle.bean.DomaCarBean;
import com.example.autoTest.doma_vehicle.bean.DomaOwnerBean;
import com.example.autoTest.doma_vehicle.bean.DomaOwnerCarBean;
import com.example.autoTest.doma_vehicle.dao.OwnerDao;
import com.example.autoTest.doma_vehicle.domain.Owner;
import com.example.autoTest.doma_vehicle.domain.OwnerCar;
import org.seasar.doma.jdbc.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomaOwnerService {

  private final OwnerDao ownerDao;

  public DomaOwnerService(OwnerDao ownerDao) {
    this.ownerDao = ownerDao;
  }

  // cRud -------------------------------------------------------
  public List<DomaOwnerCarBean> selectAll() {
    List<DomaOwnerCarBean> domaOwnerCarBeanList = new ArrayList<>();
    List<OwnerCar> ownerCarList = ownerDao.selectAll();
    ownerCarList.forEach(ownerCar -> {
      DomaOwnerCarBean domaOwnerCarBean = new DomaOwnerCarBean(
          ownerCar.getOwnerid()
          , ownerCar.getFirstname()
          , ownerCar.getLastname()
          , ownerCar.getCarId()
          , new DomaCarBean(
          ownerCar.getId()
          , ownerCar.getBrand()
          , ownerCar.getModel()
          , ownerCar.getColor()
          , ownerCar.getRegisterNumber()
          , ownerCar.getYear()
          , ownerCar.getPrice()
          , ownerCar.getOwnerId()
        )
      );
      domaOwnerCarBeanList.add(domaOwnerCarBean);
    });

    return domaOwnerCarBeanList;
  }

  public List<DomaOwnerBean> selectAllOnlyOwner() {
    List<DomaOwnerBean> domaOwnerBeanList = new ArrayList<>();
    ownerDao
            .selectAllOnlyOwner()
            .forEach(owner -> {
              DomaOwnerBean domaOwnerBean = new DomaOwnerBean(
                      owner.getCarId()
                      , owner.getFirstname()
                      , owner.getLastname()
                      , owner.getOwnerid()
              );
              domaOwnerBeanList.add(domaOwnerBean);
            });
    return domaOwnerBeanList;
  }

  // Crud -------------------------------------------------------
  public DomaOwnerBean save(DomaOwnerBean domaOwnerBean) {

    Owner owner = new Owner(
            domaOwnerBean.getOwnerid()
            , domaOwnerBean.getFirstname()
            , domaOwnerBean.getLastname()
            , domaOwnerBean.getCarId()
    );
    Result<Owner> insertCar = ownerDao.insert(owner);
    DomaOwnerBean InsertDomaOwnerBean = new DomaOwnerBean(
            insertCar.getEntity().getOwnerid()
            , insertCar.getEntity().getFirstname()
            , insertCar.getEntity().getLastname()
            , insertCar.getEntity().getCarId()
    );
    return InsertDomaOwnerBean;

  }

}
