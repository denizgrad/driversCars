package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.ManufacturerDO;
/**
 * 
 * @author deniz.ozen
 *
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>
{

	ManufacturerDO findByName(String manufacturerName);

}
