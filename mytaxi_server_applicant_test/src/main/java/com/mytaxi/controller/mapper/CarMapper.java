package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
	public static CarDTO convert(CarDO carDo) {
		return new CarDTO(carDo);
	}

	public static CarDO convert(CarDTO source) {
		CarDO car = new CarDO();
		car.setId(source.getId());
		car.setConvertible(source.getConvertible());
		car.setEngineType(source.getEngineType());
		car.setLicensePlate(source.getLicensePlate());
		ManufacturerDO manufacturer = new ManufacturerDO();
		manufacturer.setName(source.getManufacturer());
		car.setManufacturer(manufacturer);
		car.setRating(source.getRating());
		car.setSeatCount(source.getSeatCount());
		return car;
	}

	public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
		return cars.stream().map(CarMapper::convert).collect(Collectors.toList());
	}
}
