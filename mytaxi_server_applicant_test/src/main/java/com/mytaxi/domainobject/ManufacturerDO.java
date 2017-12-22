package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table( name =  "manufacturer")
public class ManufacturerDO extends AModel{
    @Column(nullable = false)
    @NotNull(message = "Manufacturer name can not be null!")
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ManufacturerDO(String name) {
		super();
		this.name = name;
	}

	public ManufacturerDO() {
		super();
	}
	
    
}
