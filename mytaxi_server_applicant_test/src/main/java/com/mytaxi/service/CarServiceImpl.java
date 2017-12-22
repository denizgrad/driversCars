package com.mytaxi.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some driver specific things.
 * <p/>
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public CarDO findCarById(Long carId) throws EntityNotFoundException {
		CarDO car = isExist(carId);
		return car;
	}

	private CarDO isExist(final Long carId) throws EntityNotFoundException {
		CarDO car = carRepository.findOne(carId);
		if (null == car) {
			throw new EntityNotFoundException("Could not find CAR entity with id: " + carId);
		}
		return car;
	}

	private ManufacturerDO isExist(final String manufacturerName) throws EntityNotFoundException {
		ManufacturerDO manufacturer = manufacturerRepository.findByName(manufacturerName);
		if (null == manufacturer) {
			return new ManufacturerDO(manufacturerName);
		}
		return manufacturer;
	}

	@Override
	public Collection<CarDO> findAllCars() {

		return StreamSupport.stream(carRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public CarDO create(CarDO car) throws EntityNotFoundException {
		ManufacturerDO man = isExist(car.getManufacturer().getName());
		car.setManufacturer(man);
		return carRepository.save(car);
	}

	@Override
	public void update(CarDO car) throws EntityNotFoundException {
		CarDO updateCar = isExist(car.getId());
		ManufacturerDO man = isExist(car.getManufacturer().getName());
		updateCar.setManufacturer(man);
		updateCar.setConvertible(car.getConvertible());
		updateCar.setEngineType(car.getEngineType());
		updateCar.setLicensePlate(car.getLicensePlate());
		updateCar.setRating(car.getRating());
		updateCar.setSeatCount(car.getSeatCount());
		updateCar.setSelectedDriver(car.getSelectedDriver());
		carRepository.save(updateCar);
	}

	/**
	 * brakes relations
	 */
	@Override
	public void delete(Long carId) throws EntityNotFoundException {
		CarDO car = isExist(carId);
		if(car.getSelectedDriver() != null) {
			car.getSelectedDriver().setSelectedCar(null);
			driverRepository.save(car.getSelectedDriver());
			car.setSelectedDriver(null);
			this.update(car);
		}
		carRepository.delete(carId);
	}

}
