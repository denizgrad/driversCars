package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotFreeException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.DriverCarService;
import com.mytaxi.service.DriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private DriverCarService driverCarService;

	@PostMapping("/select")
	public DriverDTO selectCarByDriverId(@RequestParam long driverId, @RequestParam long carId)
			throws EntityNotFoundException, CarAlreadyInUseException, DriverNotFreeException {

		return driverCarService.selectCarByDriver(driverId, carId);
	}

	@DeleteMapping("/deselect")
	public void deselectCarByDriverId(@RequestParam long driverId, @RequestParam long carId)
			throws EntityNotFoundException, CarAlreadyInUseException, DriverNotFreeException {
		driverCarService.deSelectCarByDriver(driverId, carId);
	}

	@GetMapping("/{driverId}")
	public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
		return DriverMapper.makeDriverDTO(driverService.findDriverById(driverId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
		DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
		driverService.create(driverDO);
		return DriverMapper.makeDriverDTO(driverDO);
	}

	@DeleteMapping("/{driverId}")
	public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
		driverService.delete(driverId);
	}

	@PutMapping("/{driverId}")
	public void updateLocation(@Valid @PathVariable long driverId, @RequestParam double longitude,
			@RequestParam double latitude) throws ConstraintsViolationException, EntityNotFoundException {
		driverService.updateLocation(driverId, longitude, latitude);
	}

	@GetMapping
	public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
			throws ConstraintsViolationException, EntityNotFoundException {
		return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
	}
	
	@GetMapping
	@RequestMapping("/seatCount")
	public List<DriverDTO> findDriversBySeatCount(@RequestParam int seatCount)
			throws ConstraintsViolationException, EntityNotFoundException {
		return DriverMapper.makeDriverDTOList(driverService.findBySeatCount(seatCount));
	}
}
