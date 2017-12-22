package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {

	@JsonProperty("car")
	private CarDTO carDto;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Username can not be null!")
	private String username;

	@NotNull(message = "Password can not be null!")
	private String password;

	private GeoCoordinate coordinate;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DriverDTO) {
			DriverDTO test = (DriverDTO) obj;
			return !StringUtils.isEmpty(this.username) && !StringUtils.isEmpty(test.username)
					? Objects.equals(this.username, test.username)
					: super.equals(obj);
		}
		return super.equals(obj);
	}

	private DriverDTO() {
	}

	private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, CarDTO carDto) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.coordinate = coordinate;
		this.carDto = carDto;
	}

	public static DriverDTOBuilder newBuilder() {
		return new DriverDTOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public GeoCoordinate getCoordinate() {
		return coordinate;
	}

	public static class DriverDTOBuilder {
		private Long id;
		private String username;
		private String password;
		private GeoCoordinate coordinate;
		private CarDTO carDto;
		
		public DriverDTOBuilder setSelectedCar(CarDTO carDto) {
			this.carDto = carDto;
			return this;
		}
		
		public DriverDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public DriverDTOBuilder setUsername(String username) {
			this.username = username;
			return this;
		}

		public DriverDTOBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate) {
			this.coordinate = coordinate;
			return this;
		}
		public DriverDTOBuilder setCarDto(CarDTO carDto) {
			this.carDto = carDto;
			return this;
		}
		public DriverDTO createDriverDTO() {
			return new DriverDTO(id, username, password, coordinate, carDto);
		}

	}
}
