package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainobject.CarDO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
	
	public CarDTO() {
		super();
	}

	public CarDTO(CarDO carDo) {
		this.setId(carDo.getId());
		this.setRating(carDo.getRating());
		this.setConvertible(carDo.getConvertible());
		this.setSeatCount(carDo.getSeatCount());
		this.setEngineType(carDo.getEngineType());
		this.setLicensePlate(carDo.getLicensePlate());
		if(carDo.getManufacturer() != null) {
			this.setManufacturer(carDo.getManufacturer().getName());
		}
		if(carDo.getSelectedDriver() != null) {
			this.setDriverName(carDo.getSelectedDriver().getUsername());
		}
	}

	private String driverName;
	
	private Long id;

	private Float rating;

	private String engineType;

	private Integer seatCount;

	private Boolean convertible;

	private String licensePlate;

	private String manufacturer;

	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public Boolean getConvertible() {
		return convertible;
	}

	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
