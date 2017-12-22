package com.mytaxi.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;
import com.mytaxi.MytaxiServerApplicantTestApplication;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MytaxiServerApplicantTestApplication.class)
public class DriverRepositoryTest {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private CarRepository carRepository;

	@Test
	public void testDriverById() {
		DriverDO driver = driverRepository.findOne(1L);
		Assert.assertNotNull(driver);
	}

	@Test
	public void testAllDrivers() {
		List<DriverDO> cars = Lists.newArrayList(driverRepository.findAll());
		Assert.assertThat(cars, hasSize(8));
	}
	
	@Test
	public void testDriverCarFetch() {
		CarDO car = carRepository.findOne(9L);
		DriverDO driver = driverRepository.findOne(1L);
		car.setSelectedDriver(driver);
		driver.setSelectedCar(car);
		carRepository.save(car);
		driverRepository.save(driver);
		
		CarDO car2 = carRepository.findOne(9L);
		DriverDO driver2 = driverRepository.findOne(1L);
		Assert.assertThat(car2.getSelectedDriver().getUsername(), is(driver.getUsername()));
		Assert.assertThat(driver2.getSelectedCar().getLicensePlate(), is(car.getLicensePlate()));
	}
	

}
