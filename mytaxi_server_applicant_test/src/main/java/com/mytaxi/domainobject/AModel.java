package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class AModel {
	@Id
    @GeneratedValue
	protected Long id;
	
    @Column(nullable = false, name = "date_created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    public ZonedDateTime getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }
}
