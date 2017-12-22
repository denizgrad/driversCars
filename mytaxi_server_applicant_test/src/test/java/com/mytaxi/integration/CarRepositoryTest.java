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
import com.mytaxi.domainobject.CarDO;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MytaxiServerApplicantTestApplication.class)
public class CarRepositoryTest {
	@Autowired
	private CarRepository carRepository;

	@Test
	public void testCarById() {
		CarDO car = carRepository.findOne(9L);
		Assert.assertNotNull(car);
	}

	@Test
	public void testAllCars() {
		List<CarDO> cars = Lists.newArrayList(carRepository.findAll());
		Assert.assertThat(cars, hasSize(2));
	}
	
	@Test
	public void testManufacturerFetch() {
		CarDO car = carRepository.findOne(9L);
		Assert.assertThat(car.getManufacturer().getName(), is("Lada"));
	}
	

}
