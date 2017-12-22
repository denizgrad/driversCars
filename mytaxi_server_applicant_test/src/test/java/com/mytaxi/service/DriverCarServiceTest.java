package com.mytaxi.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.mytaxi.ATest;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.DriverNotFreeException;
import com.mytaxi.exception.EntityNotFoundException;

public class DriverCarServiceTest extends ATest{
	@Mock
	private CarService carService;

	@Mock
	private DriverService driverService;

	@InjectMocks
	private DriverCarServiceImpl driverCarService;
	
	@Test
	public void selectCarByDriverTest() throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException {
		CarDO car = getCarDO();
		DriverDO driver = getDriverDO();
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		doNothing().when(carService).update(car);
		
		when(driverService.findDriverById(any(Long.class))).thenReturn(driver);
		
		driverCarService.selectCarByDriver(1L, 1L);
		
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(carService, times(1)).update(car);
		verify(driverService, times(1)).findDriverById(any(Long.class));
	}
	
	@Test(expected = DriverNotFreeException.class)
	public void selectCarByDriverTestCarInUse() throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException {
		CarDO car = getCarDO();
		DriverDO driver = getDriverDO();
		driver.setSelectedCar(car);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		doNothing().when(carService).update(car);
		when(driverService.findDriverById(any(Long.class))).thenReturn(driver);
		
		driverCarService.selectCarByDriver(1L, 1L);
		
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(carService, times(1)).update(car);
		verify(driverService, times(1)).findDriverById(any(Long.class));
	}
	
	@Test(expected = CarAlreadyInUseException.class)
	public void selectCarByDriverTestDriverNotFree() throws CarAlreadyInUseException, EntityNotFoundException, DriverNotFreeException {
		CarDO car = getCarDO();
		DriverDO driver = getDriverDO();
		car.setSelectedDriver(driver);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		doNothing().when(carService).update(car);
		when(driverService.findDriverById(any(Long.class))).thenReturn(driver);
		
		driverCarService.selectCarByDriver(1L, 1L);
		
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(carService, times(1)).update(car);
		verify(driverService, times(1)).findDriverById(any(Long.class));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void deselectCarByDriverTestCar() throws EntityNotFoundException, CarAlreadyInUseException, DriverNotFreeException {
		CarDO car = getCarDO();
		DriverDO driver = getDriverDO();
		car.setSelectedDriver(driver);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		doNothing().when(carService).update(car);
		
		when(driverService.findDriverById(any(Long.class))).thenReturn(driver);
		
		driverCarService.deSelectCarByDriver(1L, 1L);
		
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(carService, times(1)).update(car);
		verify(driverService, times(1)).findDriverById(any(Long.class));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void deselectCarByDriverTestDriver() throws EntityNotFoundException, CarAlreadyInUseException, DriverNotFreeException {
		CarDO car = getCarDO();
		DriverDO driver = getDriverDO();
		driver.setSelectedCar(car);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		doNothing().when(carService).update(car);
		
		when(driverService.findDriverById(any(Long.class))).thenReturn(driver);
		
		driverCarService.deSelectCarByDriver(1L, 1L);
		
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(carService, times(1)).update(car);
		verify(driverService, times(1)).findDriverById(any(Long.class));
	}
	
	@Test
	public void deselectCarByDriverTest() throws EntityNotFoundException, CarAlreadyInUseException, DriverNotFreeException {
		CarDO car = getCarDO();
		DriverDO driver = getDriverDO();
		driver.setSelectedCar(car);
		car.setSelectedDriver(driver);
		when(carService.findCarById(any(Long.class))).thenReturn(car);
		doNothing().when(carService).update(car);
		
		when(driverService.findDriverById(any(Long.class))).thenReturn(driver);
		
		driverCarService.deSelectCarByDriver(1L, 1L);
		
		verify(carService, times(1)).findCarById(any(Long.class));
		verify(carService, times(1)).update(car);
		verify(driverService, times(1)).findDriverById(any(Long.class));
	}
	
}

