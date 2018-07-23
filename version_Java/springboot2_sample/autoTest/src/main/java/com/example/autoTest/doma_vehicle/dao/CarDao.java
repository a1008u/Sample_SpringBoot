package com.example.autoTest.doma_vehicle.dao;

import com.example.autoTest.doma_vehicle.domain.Car;
import com.example.autoTest.doma_vehicle.domain.OwnerCar;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@ConfigAutowireable
@Dao
public interface CarDao {

  @Select
  List<OwnerCar> selectAll();

  @Select
  List<Car> selectAllOnlyCar();

  @Select
  Stream<OwnerCar> selectAllStream();

  @Insert
  @Transactional
  Result<Car> insert(Car car);
}
