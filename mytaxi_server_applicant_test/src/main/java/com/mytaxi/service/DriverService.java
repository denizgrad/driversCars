package com.mytaxi.service;

import java.util.Collection;
import java.util.List;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface DriverService
{

    DriverDO findDriverById(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;
    
    void updateCar(DriverDO driver) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

	DriverDO isExist(Long driverId) throws EntityNotFoundException;

	Collection<DriverDO> findBySeatCount(int seatCount);

}
