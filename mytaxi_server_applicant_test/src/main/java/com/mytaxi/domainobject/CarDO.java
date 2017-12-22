package com.mytaxi.domainobject;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.springframework.util.StringUtils;

@Entity
@Table(
    name = "car"
)
public class CarDO extends AModel
{

    @Column
    private Float rating;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column
    private Boolean convertible;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinColumn(name="manufacturer")
    private ManufacturerDO manufacturer;

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinColumn(name="driver")
    private DriverDO selectedDriver;
    
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CarDO) {
			CarDO test = (CarDO)obj;
			return !StringUtils.isEmpty(this.id) && !StringUtils.isEmpty(test.id) ?  
							Objects.equals(this.id, test.id): super.equals(obj);
		}
		return super.equals(obj);
	}
    //SG
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

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
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

	public ManufacturerDO getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerDO manufacturer) {
		this.manufacturer = manufacturer;
	}

	public DriverDO getSelectedDriver() {
		return selectedDriver;
	}

	public void setSelectedDriver(DriverDO selectedDriver) {
		this.selectedDriver = selectedDriver;
	}

}
