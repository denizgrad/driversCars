package com.mytaxi;

import java.io.IOException;
import java.time.ZonedDateTime;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

@RunWith(MockitoJUnitRunner.class)
public abstract class ATest {
	
	protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
	
	public CarDO getCarDO() {
		CarDO car = new CarDO();
		car.setLicensePlate("PLAKA");
		car.setEngineType("BENZIN");
		car.setId(1L);
		car.setSeatCount(2);
		car.setRating(11.0F);
		car.setDateCreated(ZonedDateTime.now());
		car.setConvertible(true);
		ManufacturerDO manufacturer = new ManufacturerDO();
		manufacturer.setName("lada");
		manufacturer.setId(1L);
		manufacturer.setDateCreated(ZonedDateTime.now());
		car.setManufacturer(manufacturer);
		return car;
	}

	public ManufacturerDO getManufacturer() {
		ManufacturerDO manufacturer = new ManufacturerDO();
		manufacturer.setDateCreated(ZonedDateTime.now());
		manufacturer.setId(1L);
		manufacturer.setName("lada");
		return manufacturer;
	}

	public CarDTO getCarDTO() {
		return new CarDTO(getCarDO());
	}

	public DriverDO getDriverDO() {
		DriverDO driver = new DriverDO("test", "test");
		driver.setId(1L);
		driver.setDateCreated(ZonedDateTime.now());
		driver.setOnlineStatus(OnlineStatus.ONLINE);
		GeoCoordinate geoCoordinate = new GeoCoordinate(0, 0);
		driver.setCoordinate(geoCoordinate);
		return driver;
	}

	public DriverDTO getDriverDTO() {
		return DriverMapper.makeDriverDTO(getDriverDO());
	}

}
