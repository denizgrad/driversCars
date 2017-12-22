package com.mytaxi.service;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.DriverNotFreeException;
import com.mytaxi.exception.EntityNotFoundException;
/**
 * Facade between car and driver
 * @author deniz.ozen
 *
 */
public interface DriverCarService {

	DriverDTO selectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException;

	void deSelectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException;
}
