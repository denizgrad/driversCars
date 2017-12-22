package com.mytaxi.service;


import java.util.Collection;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.EntityNotFoundException;
/**
 * 
 * @author deniz.ozen
 *
 */
public interface CarService
{

    CarDO findCarById(final Long carId) throws EntityNotFoundException;

    Collection<CarDO> findAllCars();

    CarDO create(final CarDO car) throws EntityNotFoundException;

    void update(final CarDO car) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;


}
