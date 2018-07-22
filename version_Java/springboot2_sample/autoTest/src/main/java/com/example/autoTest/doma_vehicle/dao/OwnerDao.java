package com.example.autoTest.doma_vehicle.dao;

import com.example.autoTest.doma_vehicle.domain.Owner;
import com.example.autoTest.doma_vehicle.domain.OwnerCar;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface OwnerDao {

  @Select
  List<Owner> selectAllOnlyOwner();

  @Select
  List<OwnerCar> selectAll();

  @Insert
  @Transactional
  Result<Owner> insert(Owner owner);

}
