package com.mytaxi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.DriverNotFreeException;
import com.mytaxi.exception.EntityNotFoundException;

@Service
@Transactional
public class DriverCarServiceImpl implements DriverCarService {

	@Autowired
	DriverService driverService;
	@Autowired
	CarService carService;

	@Override
	public DriverDTO selectCarByDriver(Long driverId, Long carId)
			throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException {
		DriverDO driverDO = driverService.findDriverById(driverId);
		if (driverDO.getSelectedCar() != null || driverDO.getOnlineStatus().equals(OnlineStatus.OFFLINE)) {
			throw new DriverNotFreeException();
		}
		CarDO carDO = carService.findCarById(carId);
		if (carDO.getSelectedDriver() != null) {
			throw new CarAlreadyInUseException();
		}

		driverDO.setSelectedCar(carDO);
		carDO.setSelectedDriver(driverDO);
		carService.update(carDO);
		driverService.updateCar(driverDO);
		return DriverMapper.makeDriverDTO(driverDO);
	}

	@Override
	public void deSelectCarByDriver(Long driverId, Long carId)
			throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException {
		DriverDO driverDO = driverService.findDriverById(driverId);
		if (driverDO.getSelectedCar() == null) {
			throw new EntityNotFoundException("Driver has no car");
		}
		CarDO carDO = carService.findCarById(carId);
		if (carDO.getSelectedDriver() == null) {
			throw new EntityNotFoundException("Car has no driver");
		}
		driverDO.setSelectedCar(null);
		carDO.setSelectedDriver(null);
		carService.update(carDO);
		driverService.updateCar(driverDO);
	}

}
