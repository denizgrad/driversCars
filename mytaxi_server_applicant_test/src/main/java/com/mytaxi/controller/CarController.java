package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import com.mytaxi.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @RestController Car controller.
 * @author deniz.ozen
 *
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/{carId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CarDTO> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
		return new ResponseEntity<>(CarMapper.convert(carService.findCarById(carId)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CarDTO>> getAllCars() {

		return new ResponseEntity<>(CarMapper.makeCarDTOList(carService.findAllCars()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO CarDto) throws EntityNotFoundException {
		CarDO car = CarMapper.convert(CarDto);
		carService.create(car);
		return new ResponseEntity<>(CarMapper.convert(car),	HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> updateCar(@Valid @RequestBody CarDTO CarDto) throws EntityNotFoundException {
		carService.update(CarMapper.convert(CarDto));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
		carService.delete(carId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
