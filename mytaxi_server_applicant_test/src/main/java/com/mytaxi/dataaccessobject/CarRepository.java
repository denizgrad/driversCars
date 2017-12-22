package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author deniz.ozen
 *
 */
public interface CarRepository extends CrudRepository<CarDO, Long> {

}
