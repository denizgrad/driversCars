package com.mytaxi.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytaxi.ATest;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * 
 * @author deniz.ozen
 *
 */
public class CarServiceTest extends ATest {
	@Mock
	private CarRepository carRepository;

	@Mock
	private ManufacturerRepository manufacturerRepository;

	@InjectMocks
	private CarServiceImpl carService;

	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(CarServiceImpl.class);
	}

	@Test
	public void testFindCarById() throws EntityNotFoundException {
		CarDO car = getCarDO();
		when(carRepository.findOne(any(Long.class))).thenReturn(car);
		carService.findCarById(any(Long.class));
		verify(carRepository, times(1)).findOne(any(Long.class));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindCarByIdException() throws EntityNotFoundException {
		when(carRepository.findOne(any(Long.class))).thenReturn(null);
		carService.findCarById(any(Long.class));
		verify(carRepository, times(1)).findOne(any(Long.class));
	}

	@Test
	public void testFindAllCars() {
		List<CarDO> cars = Arrays.asList(getCarDO());
		when(carRepository.findAll()).thenReturn(cars);
		carService.findAllCars();
		verify(carRepository, times(1)).findAll();
	}

	@Test
	public void testCreate() throws EntityNotFoundException {
		CarDO car = getCarDO();
		ManufacturerDO manufacturer = getManufacturer();
		when(manufacturerRepository.findByName(any(String.class))).thenReturn(manufacturer);
		when(carRepository.save(any(CarDO.class))).thenReturn(car);
		carService.create(car);
		verify(manufacturerRepository, times(1)).findByName(any(String.class));
		verify(carRepository, times(1)).save(car);
	}

	@Test
	public void testUpdate() throws Exception {
		CarDO car = getCarDO();
		ManufacturerDO manufacturer = getManufacturer();
		when(carRepository.findOne(any(Long.class))).thenReturn(car);
		when(manufacturerRepository.findByName(any(String.class))).thenReturn(manufacturer);
		carService.update(car);
		verify(carRepository, times(1)).findOne(any(Long.class));
		verify(manufacturerRepository, times(1)).findByName(any(String.class));
	}

	@Test
	public void testDelete() throws EntityNotFoundException {
		CarDO car = getCarDO();
		when(carRepository.findOne(any(Long.class))).thenReturn(car);
		carService.delete(1L);
		verify(carRepository, times(1)).findOne(any(Long.class));
	}
}
